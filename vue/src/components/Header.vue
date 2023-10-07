<template>
    <div style="font-size: 15px; display: flex;">
        <div style="line-height:center">
            <span :class="[collapseBtnClass,isCollapse]" style="cursor:pointer;font-size: 25px" @click="collapse()"></span>
        </div>

        <div style="margin-left:1%;width:60%;margin-top:15px">
            <el-breadcrumb separator="|" style="font-size:15px;">
                <el-breadcrumb-item><a href="/"><span style="font-size:20px;">首页</span></a></el-breadcrumb-item>
                <div style="margin-top:5px">
                    <el-breadcrumb-item><span>活动详情</span></el-breadcrumb-item>
                    <el-breadcrumb-item><span>活动详情</span></el-breadcrumb-item>
                    <el-breadcrumb-item><span>活动详情</span></el-breadcrumb-item>
                </div>
            </el-breadcrumb>
        </div>

        <!-- 头像 -->
        <div class="demo-basic--circle" style="margin-left: 25%;margin-top:10px">
            <div class="block"><el-avatar :size="40" :src="currentAvatar"></el-avatar></div>
        </div>
        
        <div style="margin-left: 10px">
            <el-dropdown >
                 
                <span style="font-size:18px;">{{currentUserName}}</span>
                <i class="el-icon-arrow-down" style="margin-left: 10px;margin-right: 15px"></i>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>个人信息</el-dropdown-item>

                    <el-popconfirm
                    confirm-button-text='好的'
                    cancel-button-text='不用了'
                    icon="el-icon-info"
                    icon-color="red"
                    title="这是一段内容确定删除吗？"
                    >
                        
                    </el-popconfirm>
                    <el-dropdown-item @click.native="exitUser()">退出</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
        
  </div>
</template>

<script>
    export default {
        name:'Header',
        data() {
            return {
                collapseBtnClass:'el-icon-s-unfold',//收缩图标样式,
                isCollapse:false,
             
                username:'',
                avatar:'',
                isFirstAvatar:true,
                isFirstUsername:true

            }
        },
        mounted(){
            this.$bus.$on("changeAvatar",this.changeAvatar)
        },
        beforeDestroy(){
            this.$bus.$off('changeAvatar')
        },
        methods: {
            collapse(){
                this.isCollapse=!this.isCollapse;
                this.$bus.$emit('collapse',this.isCollapse)
                if(this.isCollapse){//折叠
                    this.collapseBtnClass='el-icon-s-fold'
                    this.$bus.$emit('changeASideWidth','63px')
                }else{//展开
                    this.collapseBtnClass='el-icon-s-unfold'
                    this.$bus.$emit('changeASideWidth','220px')
                }
            },
            exitUser(){
                this.$router.push('/')
            },
            changeAvatar(avatar){
                this.avatarUrl =  'http://localhost/imgs/'+avatar
                console.log('===========',this.avatarUrl);
            }
        },
        computed:{
            currentAvatar(){ //头像地址
                if(this.isFirstAvatar){
                    this.isFirstAvatar=!this.isFirstAvatar
                    if(this.$route.params.avatar!=null||this.$route.params.avatar!=''){
                        this.avatar='http://localhost/imgs/'+this.$route.params.avatar
                        return 'http://localhost/imgs/'+this.$route.params.avatar
                    }else{
                        this.avatar='http://localhost/imgs/'+this.$route.params.avatar
                        return this.currentAvatar = 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
                    }
                    
                }else return this.avatar
                
               
            },
            currentUserName(){
                if(this.isFirstUsername){
                    this.isFirstUsername=!this.isFirstUsername
                    this.username = this.$route.params.username
                    return this.$route.params.username
                }else return this.username
            }
        }
        
    }
</script>

<style soped>
    .el-header {
      background-color: #B3C0D1;
      color: #333;
      line-height: 60px;
    }
</style>