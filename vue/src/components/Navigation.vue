<template>
    <el-menu :default-openeds="['1', '3']" 
                    style="height: 100%; " 
                    background-color='rgb(48, 65, 86)' 
                    text-color="#fff" 
                    active-text-color="#ffd04b"
                    :collapse-transition='false'
                    :collapse="isCollapse"
                    router
            >
        <div style="height:60px;line-height:60px;text-align:center;position:relative; top:10px;">
        <img src="../assets/logo.png" alt="" style="width: 18px; position:relative; top:3px;margin-right:5px;margin-left:5px;">
        <b style="color:#fff" v-show="!isCollapse">后台管理系统</b>
        </div>
        <el-menu-item index="/home">
                    <i class="el-icon-house"></i><span slot="title">主页</span>
        </el-menu-item>
        <el-submenu index="/user">
            <template slot="title">
                <i class="el-icon-setting"></i>
                <span slot="title">系统管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="/user"><i class="el-icon-user"></i>用户管理</el-menu-item>
                <el-menu-item index="/test"><i class="el-icon-cpu"></i>组件测试</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
    </el-menu>
</template>

<script>
    export default {
        name:'Navigation',
        data() {
            return {
                isCollapse:false,                   //是否收缩
            }
        },
        methods:{
            collapse(isCollapse){ //导航栏折叠
                this.isCollapse=isCollapse
            },
        },
        mounted(){
            this.$bus.$on("collapse",this.collapse)
        },
        beforeDestroy(){
            this.$bus.$off('collapse')
        }
    }
</script>

<style>

</style>