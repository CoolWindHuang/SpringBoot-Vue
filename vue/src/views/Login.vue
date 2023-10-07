<template>
  <div class="wrapper">
    <div class="center" >
        
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm myForm " >
            <div class="myFont myTitle" >登 &nbsp;&nbsp;录</div>
            <div style="margin-left:15px;margin-top:10px" class="">
                <el-form-item label="账 号" prop="username" class="loginSize">
                    <el-input v-model.number="ruleForm.username"></el-input>
                </el-form-item>
                 <el-form-item label="密 码" prop="password" class="loginSize " >
                    <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item style="padding-left:60px">
                    <el-button type="primary" style="margin-right:20px" @click="submitForm('ruleForm')">登 录</el-button>
                    <el-button type="primary" style="" @click="resetForm()">注 册</el-button>
                    <!--    <el-button @click="resetForm('ruleForm')">重 置</el-button> -->
                </el-form-item>
            </div>
           
        </el-form>
    </div>
  </div>
</template>

<script>
    import axios from 'axios'

    export default{
        name:'Login',
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
            return {
                ruleForm: {
                    password: '',
                    username: ''
                },
                rules: {
                password: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                username: [
                    { validator: checkUsername, trigger: 'blur' }
                ]
                }
            };
            },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(this.ruleForm);
                        
                        axios.post('http://localhost/login',this.ruleForm).then(
                            response =>{
                                if(response.data.msg==='success'){
                                    this.$message({
                                        message: '欢迎您： '+response.data.currentUser.nickname,
                                        type: 'success'
                                    });
                                    console.log(response.data.currentUser);
                                    this.$bus.$emit("changeAvatar",response.data.currentUser.avatar)
                                    this.$router.replace({
                                       name: "Home",
                                       params:{
                                         avatar:response.data.currentUser.avatar,
                                         username:response.data.currentUser.username,
                                       }
                                       
                                    })
                                    console.log(this.$router);
                                
                                }else{
                                    this.$message({
                                        message: '用户名或密码错误',
                                        type: 'error'
                                    });
                                }
                            },
                            error=>{
                                this.$message({
                                    message: '请联系管理员',
                                    type: 'error'
                                });
                            }
                        )
                    } else {
                        this.$message({
                            message: '请检查用户名和密码',
                            type: 'warning'
                        });
                        return false;
                    }
                });
                
            },
            resetForm() {
                console.log("register");
                this.$router.push('/register')
            },
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