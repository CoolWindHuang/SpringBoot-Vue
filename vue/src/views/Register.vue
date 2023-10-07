<template>
  <div class="wrapper">
    <div class="center" >
        
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm myForm " >
            <div class="myFont myTitle" >注 &nbsp;&nbsp;册</div>
            <div style="margin-left:15px;margin-top:10px" class="">
                <el-form-item label="账 号" prop="username" class="loginSize">
                    <el-input v-model.number="ruleForm.username"></el-input>
                </el-form-item>
                 <el-form-item label="密 码" prop="pass" class="loginSize " >
                    <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass" class="loginSize">
                    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>
                
                <el-form-item style="padding-left:60px">
                    <el-button type="primary" style="margin-right:20px" @click="registerForm('ruleForm')">注 册</el-button>
                    <el-button type="primary" style="" @click="back">返 回</el-button>
                    <!--    <el-button @click="resetForm('ruleForm')">重 置</el-button> -->
                </el-form-item>
            </div>
           
        </el-form>
    </div>
  </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name:'Register',
        data() {
            var checkUsername = (rule, value, callback) => {
                if (!value) {
                return callback(new Error('账号不能为空'));
                }
                callback();
            };
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                callback(new Error('请输入密码'));
                } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.pass) {
                callback(new Error('两次输入密码不一致!'));
                } else {
                callback();
                }
            };
            return {
                ruleForm: {
                    pass: '',
                    checkPass: '',
                    username: ''
                },
                rules: {
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validatePass2, trigger: 'blur' }
                ],
                username: [
                    { validator: checkUsername, trigger: 'blur' }
                ]
                }
            };
            },
            methods: {
            registerForm(formName) {
                console.log("registerForm");
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
    
            },
            back() {
                this.$router.push('/login')
            }
        }
        
    }
</script>

<style scoped>
    .wrapper{
        height: 100%;
        width: 100%;
        overflow: hidden;
        background-image: url('../assets/login.png');
    }
    .center{
        margin-top:15%;
        margin-left:30%;
    }
    .myborder{
        border-radius: 4px;
        border: 1px solid gainsboro;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
    }
    .myForm{
        width: 500px;
        height: 250px;
    }
    .myFont{
        font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
        font-size: 25px;
        color: white;
    }
    .loginSize{
        width: 400px;
        color: white;
    }
    .myTitle{
        text-align: center;
        margin-top: 10px;
        
    }
</style>