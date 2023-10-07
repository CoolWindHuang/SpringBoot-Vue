<template>
    <div>
        <div style="padding: 15px 0">
            <el-input v-model="username" placeholder="请输入用户名"  style="width:200px" suffix-icon='el-icon-search' ></el-input>
            <el-input v-model="email" placeholder="请输入邮箱" size="small" style="width:200px; margin-left: 5px" suffix-icon='el-icon-message'></el-input>
            <el-input v-model="address" placeholder="请输入地址"  style="width:200px; margin-left: 5px" suffix-icon='el-icon-position'></el-input>
            <el-button style="margin-left: 5px" type="primary" 
            @click="load(currentPage,pageSize,username,email,address)">
            搜素
            </el-button>
        </div>

        <div>
            <el-button style="" type="primary" @click="addDialogFormVisible = true">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button style="margin-left: 10px;" type="danger" @click="deleteBatchDialogFormVisible=true">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            <el-button style="margin-left: 10px" type="primary" >导入 <i class="el-icon-bottom"></i></el-button>
            <el-button style="margin-left: 10px" type="primary" >导出 <i class="el-icon-top"></i></el-button>
        </div>
        
        <el-table :data="tableData" style='margin-top:15px' border stripe @row-click="onRowClick" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="id" width="100"></el-table-column>
            <el-table-column prop="username" label="用户名" width="140"></el-table-column>
            <el-table-column prop="nickname" label="昵称" width="140"></el-table-column>
            <el-table-column prop="phone" label="电话"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
            <el-table-column  label="操作">
            <el-button type="success" @click="updateDialogFormVisible = true;">编辑 <i class="el-icon-edit"></i></el-button>
            <el-button type="danger"  @click="deleteDialogFormVisible = true;">删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-table-column>
        </el-table>

        <div class="block">
            <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="pageSizes"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
            </el-pagination>
        </div>

        <!-- 以下是对话框 -->
        <!-- addUser-dialog  添加用户-->
        <el-dialog title="添加用户" :visible.sync="addDialogFormVisible" width="25%">
            <el-form :model="addForm">
            <el-form-item label="昵称:" :label-width="formLabelWidth">
                <el-input v-model="addForm.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="用户名:" :label-width="formLabelWidth">
                <el-input v-model="addForm.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码:" :label-width="formLabelWidth">
                <el-input v-model="addForm.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱:" :label-width="formLabelWidth">
                <el-input v-model="addForm.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话:" :label-width="formLabelWidth">
                <el-input v-model="addForm.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址:" :label-width="formLabelWidth">
                <el-input v-model="addForm.address" autocomplete="off"></el-input>
            </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="addDialogFormVisible = false;addUser()">确 定</el-button>
            <el-button @click="addDialogFormVisible = false">取 消</el-button>
            </div>
        </el-dialog>

        <!-- updateUser-dialog 更新用户-->
        <el-dialog title="修改用户信息" :visible.sync="updateDialogFormVisible" width="25%">
            <el-form :model="row">
            <el-form-item label="昵称:" :label-width="formLabelWidth">
                <el-input v-model="row.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="用户名:" :label-width="formLabelWidth">
                <el-input v-model="row.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱:" :label-width="formLabelWidth">
                <el-input v-model="row.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话:" :label-width="formLabelWidth">
                <el-input v-model="row.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址:" :label-width="formLabelWidth">
                <el-input v-model="row.address" autocomplete="off"></el-input>
            </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
            <el-button type="warning" @click="updateDialogFormVisible = false;updateUser()">修 改</el-button>
            <el-button @click="updateDialogFormVisible = false">取 消</el-button>
            </div>
        </el-dialog>

        <!-- removeUser-dialog 删除用户-->
        <el-dialog
            title="删除用户信息"
            :visible.sync="deleteDialogFormVisible"
            width="20%">
            <span>是否删除？</span>
            <span slot="footer" class="dialog-footer">
            <el-button type="danger" @click="deleteDialogFormVisible = false;deleteUser()">删 除</el-button>
            <el-button @click="deleteDialogFormVisible = false">取 消</el-button>
            </span>
        </el-dialog>

        <!-- removeBatchUser-dialog 批量删除用户-->
        <el-dialog
            title="批量删除用户"
            :visible.sync="deleteBatchDialogFormVisible"
            width="20%">
            <span>是否批量删除？</span>
            <span slot="footer" class="dialog-footer">
            <el-button type="danger" @click="deleteBatchDialogFormVisible = false;deleteUserBatch()">删 除</el-button>
            <el-button @click="deleteBatchDialogFormVisible = false">取 消</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import axios from 'axios';
    
    export default {
        name:'UserView',
        data() {
            return {
                tableData: [],//表格数据
                    row:{},

                    
                    //搜索内容
                    username:'',
                    email:'',
                    address:'',

                    //分页数据
                    total:0,
                    pageSizes:[5, 10, 15, 20],
                    pageSize:10,
                    currentPage:1,

                    selectOptions:[],

                    //对话框
                    //添加用户对话框
                    addDialogFormVisible:false,
                    addForm:{
                    username:'',
                    password:'',
                    nickname:'',
                    phone:'',
                    email:'',
                    address:'',
                    },
                    formLabelWidth:'30%',

                    //修改用户对话框
                    updateDialogFormVisible:false,

                    //删除用户对话框
                    deleteDialogFormVisible:false,
                    deleteBatchDialogFormVisible:false
            }
        },
        beforeMount(){
            this.load();
        },
        methods:{   
            load(currentPage,pageSize,username,email,address){ //加载用户数据 当前页，页面大小
                let flag = false;
                let url= 'http://localhost:80/user/findPage?'
                if(currentPage==null&&pageSize==null){ //第一次加载
                currentPage = this.currentPage;
                pageSize=this.pageSize;
                url=url+'pageIndex='+currentPage+'&pageSize='+pageSize;
                }else if(currentPage!=undefined && pageSize!=undefined){
                this.currentPage=currentPage
                this.pageSize=pageSize
                flag=true;
                url=url+'pageIndex='+currentPage+'&pageSize='+pageSize;
                }

                //是否带条件
                if((username!=''||email!=''||address!='')&&(username!=undefined||email!=undefined||address!=undefined)){
                if(flag){
                    url=url + '&'
                }
                url=url+'pageIndex='+this.currentPage+'&pageSize='+this.pageSize
                +'&username='+username+'&email='+email+'&address='+address;
                }
                
                console.log(url);
                axios.get(url).then(
                response =>{
                    console.log(response.data);
                    this.tableData=response.data.users
                    this.total=response.data.total
                },
                error=>{
                    
                }
                )
            },
            handleSizeChange(pageSize){//页面大小改变时触发回调
                if(this.username!=''||this.email!=''||this.address!=''){
                this.load(1,pageSize,this.username,this.email,this.address)
                }else{
                this.load(1,pageSize)
                }
                
            },
            handleCurrentChange(currentPage){//当前页改变时触发回调
                if(this.username!=''||this.email!=''||this.address!=''){
                this.load(currentPage,this.pageSize,this.username,this.email,this.address)
                }else{
                this.load(currentPage,this.pageSize)
                }
            },
            addUser(){
                let url = 'http://localhost:80/user/add?'
                let param = 'username='+this.addForm.username+'&password='+this.addForm.password+'&nickname='+this.addForm.nickname+
                        '&email='+this.addForm.email+'&phone='+this.addForm.phone+'&address='+this.addForm.address
                url+=param
                axios.post(url).then(
                response =>{
                    //刷新列表
                    this.load(this.currentPage,this.pageSize,this.username,this.email,this.address)

                    this.$message({
                    message: '添加成功！',
                    type: 'success'
                    });
                },
                error=>{
                    this.$message({
                    message: '添加失败！',
                    type: 'warning'
                    });
                })
            },
            updateUser(){
                let url = 'http://localhost:80/user/update?'
                let param = 'id='+this.row.id+'&username='+this.row.username+'&nickname='+this.row.nickname+
                        '&email='+this.row.email+'&phone='+this.row.phone+'&address='+this.row.address
                url+=param;
                axios.post(url).then(
                response =>{
                    //刷新列表
                    this.load(this.currentPage,this.pageSize,this.username,this.email,this.address)

                    this.$message({
                    message: '修改成功！',
                    type: 'success'
                    });
                },
                error=>{
                    this.$message({
                    message: '修改失败！',
                    type: 'warning'
                    });
                }
                )
            },
            deleteUser(){//删除用户
            
                let url = 'http://localhost:80/user/delete/'+this.row.id
                axios.post(url).then(
                response =>{
                    //刷新列表
                    this.load(this.currentPage,this.pageSize,this.username,this.email,this.address)

                    this.$message({
                    message: '删除成功！',
                    type: 'success'
                    });
                },
                error=>{
                    this.$message({
                    message: '删除失败！',
                    type: 'warning'
                    });
                }
                )
            },
            deleteUserBatch(){//批量删除用户
                console.log('selectOptions',this.selectOptions.length==0);
                if(this.selectOptions.length==0){
                    this.$message({
                        message: '批量删除失败！您未选择任何一条数据...',
                        type: 'warning'
                        });
                    return;
                }
                let Ids = this.selectOptions.map(obj => obj.id)
                let url = '/user/deleteBatch'
                axios.post(url,{Ids}).then(
                response =>{
                    //刷新列表
                    this.load(this.currentPage,this.pageSize,this.username,this.email,this.address)

                    this.$message({
                        message: '删除成功！',
                        type: 'success'
                    });
                },
                error=>{
                    this.$message({
                        message: '删除失败！',
                        type: 'warning'
                    });
                }
                )
            },
            //获取选中的行数据
            onRowClick(row, event, column) {
                console.log("row", row);
                this.row=row;
            },
            handleSelectionChange(selectOptions){
                this.selectOptions = selectOptions;
                console.log(this.selectOptions);
            }
        },
        watch: {
            // 如果 `question` 发生改变，这个函数就会运行
            question: function (newQuestion, oldQuestion) {
                this.answer = 'Waiting for you to stop typing...'
                this.debouncedGetAnswer()
            }
        },
        
    }
</script>

<style>

</style>