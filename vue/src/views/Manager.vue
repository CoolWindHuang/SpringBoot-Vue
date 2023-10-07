<template>
  <div style="height: 100%;">
    <el-container style="height: 100%;">

      <!-- 左侧导航栏 -->
      <el-aside :width="aSideWidth"  style="background-color: rgb(238, 241, 246); height:100%">
        <Navigation/>
      </el-aside>
      
      <!-- 中部主体 -->
      <el-container>
        
        <!-- 主体顶部 -->
        <el-header>
          <Header/>
        </el-header>
        
        <!-- 数据展示区 -->
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import Navigation from '../components/Navigation.vue'
  import Header from '../components/Header.vue'
  import UserView from '../components/UserView.vue'


  export default {
    name: 'Manager',
    components:{Navigation,Header,UserView},
    data() {
      return {
        aSideWidth:'220px',                     //初始导航栏宽度
      }
    },
    methods:{
      changeASideWidth(aSideWidth){
        this.aSideWidth = aSideWidth
      }
    },
    mounted(){
      this.$bus.$on('changeASideWidth',this.changeASideWidth)
    },
    beforeDestroy(){
      this.$bus.$off('changeASideWidth')
    },
    computed:{
            currentAvatar(){ //头像地址
               if(this.$route.params.avatar!=null||this.$route.params.avatar!=''){
                return 'http://localhost/imgs/'+this.$route.params.avatar
               }else{
                return this.currentAvatar = 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
               }
            },
            currentUserName(){
                return this.$route.params.username
            }
        }
  }
    
</script>


<style scoped>
  
    
  .el-aside {
    color: #333;
    overflow-x: hidden;
    overflow-y: hidden;

    transition: width 0.15s;
  -webkit-transition: width 0.15s;
  -moz-transition: width 0.15s;
  -webkit-transition: width 0.15s;
  -o-transition: width 0.15s;

  }

  .el-main{
    padding-top: 0px;
    padding-bottom: 0px;
  }

  .el-input{
    width: 70%;
  }

  
</style>
