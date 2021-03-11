<template>
  <div id="app">
  <!--  <img src="./assets/logo.png"> -->
  <!--  <div> -->
     <!-- <el-button @click="startHacking">点击</el-button> -->
	<!-- <el-row>
	  <el-col :span="24"><div class="grid-content bg-purple-dark"></div></el-col>
	</el-row> -->
	
	<!-- type="flex" justify="center"  排版 -->
	<!-- <el-row :gutter="10" type="flex" justify="center">
	  <el-col :span="10"><div class="grid-content bg-purple"></div></el-col>
	  <el-col :span="10"><div class="grid-content bg-purple-light"></div></el-col>
	</el-row>
	<el-row :gutter="20">
	  <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
	  <el-col :span="8"><div class="grid-content bg-purple-light"></div></el-col>
	  <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
	</el-row> -->
	<!-- 响应式布局 -->
	<!-- <el-row gutter="10">
	  <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"><div class="grid-content bg-purple">x</div></el-col>
	  <el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="11"><div class="grid-content bg-purple-light">x</div></el-col>
	  <el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="11"><div class="grid-content bg-purple">x</div></el-col>
	  <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"><div class="grid-content bg-purple-light">x</div></el-col>
	</el-row>
	<el-row :gutter="20">
		<el-col :span="6" :offset="6"><div class="grid-content bg-purple"></div></el-col>
		<el-col :span="6" :offset="6"><div class="grid-content bg-purple-light"></div></el-col>
	</el-row>
	
	<el-row gutter="20">
	  <el-col :span="10" offset="6"><div class="grid-content bg-purple"></div></el-col>
	  <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
	 
	</el-row> -->
   <!-- </div> -->
    
	<!-- <el-container>
		<el-header>Header</el-header>
		<el-main>main</el-main>
		<el-footer>footer</el-footer>
	</el-container>
	<br />
	<el-container>
		<el-header>header</el-header>
		<el-container>
			<el-aside width="100px">aside</el-aside>
		    <el-main>main</el-main>
		</el-container>
		
	</el-container>
	<br />
	<el-container>
		<el-header>header</el-header>
		<el-container>
			<el-aside width="100px">aside</el-aside>
		    <el-container>
				<el-main>main</el-main>
				<el-footer>footer</el-footer>
			</el-container>
		</el-container>
		
	</el-container> -->

	<el-container style="height: 100%; border: 1px solid #eee">
	  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
	    <el-menu :default-openeds="['1', '3']">
	      <el-submenu index="1">
	        <template slot="title"><i class="el-icon-message"></i>导航一</template>
	        <el-menu-item-group>
	          <template slot="title">分组一</template>
	          <el-menu-item index="1-1">选项1</el-menu-item>
	          <el-menu-item index="1-2">选项2</el-menu-item>
	        </el-menu-item-group>
	        <el-menu-item-group title="分组2">
	          <el-menu-item index="1-3">选项3</el-menu-item>
	        </el-menu-item-group>
	        <el-submenu index="1-4">
	          <template slot="title">选项4</template>
	          <el-menu-item index="1-4-1">选项4-1</el-menu-item>
	        </el-submenu>
	      </el-submenu>
	      <el-submenu index="2">
	        <template slot="title"><i class="el-icon-menu"></i>导航二</template>
	        <el-menu-item-group>
	          <template slot="title">分组一</template>
	          <el-menu-item index="2-1">选项1</el-menu-item>
	          <el-menu-item index="2-2">选项2</el-menu-item>
	        </el-menu-item-group>
	        <el-menu-item-group title="分组2">
	          <el-menu-item index="2-3">选项3</el-menu-item>
	        </el-menu-item-group>
	        <el-submenu index="2-4">
	          <template slot="title">选项4</template>
	          <el-menu-item index="2-4-1">选项4-1</el-menu-item>
	        </el-submenu>
	      </el-submenu>
	      <el-submenu index="3">
	        <template slot="title"><i class="el-icon-setting"></i>导航三</template>
	        <el-menu-item-group>
	          <template slot="title">分组一</template>
	          <el-menu-item index="3-1">选项1</el-menu-item>
	          <el-menu-item index="3-2">选项2</el-menu-item>
	        </el-menu-item-group>
	        <el-menu-item-group title="分组2">
	          <el-menu-item index="3-3">选项3</el-menu-item>
	        </el-menu-item-group>
	        <el-submenu index="3-4">
	          <template slot="title">选项4</template>
	          <el-menu-item index="3-4-1">选项4-1</el-menu-item>
	        </el-submenu>
	      </el-submenu>
	    </el-menu>
	  </el-aside>
	  
	  <el-container>
	    <el-header style="text-align: right; font-size: 12px">
	      <el-dropdown>
	        <i class="el-icon-setting" style="margin-right: 15px"></i>
	        <el-dropdown-menu slot="dropdown">
	          <el-dropdown-item>查看</el-dropdown-item>
	          <el-dropdown-item>新增</el-dropdown-item>
	          <el-dropdown-item>删除</el-dropdown-item>
	        </el-dropdown-menu>
	      </el-dropdown>
	      <span>王小虎</span>
	    </el-header>
	    
	    <el-main>
	      <el-table :data="tableData" >
	        <el-table-column prop="date" label="日期" width="140">
	        </el-table-column>
	        <el-table-column prop="name" label="姓名" width="120" >
	        </el-table-column>
	        <el-table-column prop="address" label="地址">
	        </el-table-column>
	      </el-table>
	    </el-main>
	  </el-container>
	</el-container>
	
	<el-link :underline="false">无下划线</el-link>
	<el-link disabled>有下划线(禁用状态)</el-link>
	<el-link href="https://element.eleme.io" target="_blank">默认链接</el-link>
    <el-link type="primary">主要链接</el-link>
    <el-link icon="el-icon-edit">编辑</el-link>
	<el-link>产看<i class="el-icon-view el-icon--right"></i></el-link>
    
	<el-button>默认按钮</el-button>
	<el-button type="primary">主要按钮</el-button>
	<el-button type="success" round>成功按钮</el-button>
	<el-button type="success" plain>朴素成功按钮</el-button>
	<el-button type="info" icon="el-icon-star-off"></el-button>
	
	<el-button type="text">文字按钮</el-button>
	<el-button type="text" disabled>文字按钮</el-button>
	<el-button type="primary">上传<i class="el-icon-upload el-icon--right"></i></el-button>
    <el-button-group>
	<el-button type="primary" icon="el-icon-arrow-left">上一页</el-button>
	<el-button type="primary">下一页<i class="el-icon-arrow-right el-icon-right"></i></el-button>
    </el-button-group>
	
	<el-button type="primary" :loading="true">加载</el-button>
	
	<el-radio-group v-model="radio">
	<el-radio  label="1">选项1</el-radio>
	<el-radio  label="2">选项2</el-radio>
	<el-radio  label="3">选项3</el-radio>
	</el-radio-group>
	
	<el-radio-group v-model="radio" size="medium" >
		<el-radio-button label="1">上海</el-radio-button>
		<el-radio-button label="2">北京</el-radio-button>
		<el-radio-button label="3">广州</el-radio-button>
		<el-radio-button label="4">深圳</el-radio-button>
	</el-radio-group>
	
	<el-checkbox-group v-model="checklist">
		<el-checkbox label="多选项1"></el-checkbox>
		<el-checkbox label="多选项2"></el-checkbox>
		<el-checkbox label="多选项3"></el-checkbox>
	</el-checkbox-group>
	
	<el-checkbox :indeterminate="isIndetermainate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
	 <div style="margin: 15px 0;"></div>
	 <el-checkbox-group v-model="checkedCities" @change="handleCheckCitiesChange">
		 <el-checkbox v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox>
	 </el-checkbox-group>
	
	<!-- 可选项目数量的限制 -->
	<el-checkbox-group
	 v-model="checkedCities"
	 :min='1'
	 :max='2'>
	 <el-checkbox v-for="city in cities" :label='city' :key="city">{{city}}</el-checkbox>
	 </el-checkbox-group>
	
	<div style="width: 200px;">
	<el-input v-model="input" placeholder="请输入内容" size="small" clearable show-password></el-input>
	<el-input placeholder="请选输入" suffix-icon="el-icon-date" v-model="input"></el-input>
	<el-input placeholder="请输入" prefix-icon="el-icon-search" v-model="input"></el-input>
	
	<el-input type="textarea" autosize :rows="2" placeholder="请输入" v-model="input"></el-input>
<!-- 复合型输入框 -->
    <el-input placeholder="请输入" v-model="input">
		<template slot="prepend">Http://</template>
	</el-input>
    
	<el-input placeholder="请输入" v-model="input">
		<template slot="append">.com</template>
	</el-input>
	</div>
 <div style="width: 600px; margin-top: 20px;">
	<el-input placeholder="请输入" v-model="input" class="input-with-select">
		<el-select v-model="select" slot="prepend" placeholder="请选择">
		<!-- 	<el-option v-for="item in cities" :key="item" :label="item" :value="item"></el-option> -->
			<el-option label="餐厅名" value="1"></el-option>
			<el-option label="订单号" value="2"></el-option>
			<el-option label="用户电话" value="3"></el-option>
		</el-select>
		<el-button slot="append" icon="el-icon-search"></el-button>
	</el-input>
 </div>

	  <el-input-number v-model="num" size="mini" @change="handleChange" step="2" :min="1" :max="10" label='描述文字'></el-input-number>
      <el-input-number v-model="num" controls-position="right" @change="handleChange" :min="1" :max="10"></el-input-number>
      
	  <el-select v-model="select" placeholder="请选择" clearable>
      	   <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" :disabled="item.disabled"></el-option>
      </el-select>
	  
	  <el-switch v-model="switchs" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
	  
	  <el-tooltip :content="'Switch values'+ value" placement="top">
		  <el-switch v-model="value" active-color="#13ce66" inactive-color="#ff4949" active-value="100" inactive-value="0"></el-switch>
		  
	  </el-tooltip>
	  
	  <el-slider v-model="value" show-input></el-slider>
	  
	  <el-upload
	    class="upload-demo"
		action="https://jsonplaceholder.typicode.com/posts"
		:on-preview="handlePreview"
		:on-remove="handleRemove"
		:before-remove="beforeRemove"
		multiple
		:limit="3"
        :on-exceed="handleExceed"
		:file-list="fileList"
		:on-success="handleSuccess"
	   >
	   <el-button size="small" type="primary">点击上传</el-button>
	   <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
	   </el-upload>


<!-- 表单 -->

   <el-form ref="form" :model="form" label-width="80px">
	   <el-form-item label="活动名称">
		   <el-input v-model="form.name"></el-input>
	   </el-form-item>
	   <el-form-item label="活动区域">
		   <el-select v-model="form.region" placeholder="请选择活动区域">
			   <el-option label="区域一" value="shanghai"></el-option>
			   <el-option label="区域二" value="beijing"></el-option>
		   </el-select>
	   </el-form-item>
   </el-form>
   
   
   <el-form inline="true" :model="formInline" class="demo-form-inline">
	   <el-form-item label="审批人">
		   <el-input v-model="formInline.user" placeholder="审批人"></el-input>
	   </el-form-item>
       <el-form-item label="活动区域">
		   <el-select v-model="formInline.region" placeholder="活动区域">
			   <el-option label="区域一" value="shanghai"></el-option>
			   <el-option label="区域二" value="beijing"></el-option>
		   </el-select>
	   </el-form-item>
	   <el-form-item>
	   <el-button type="primary" @click="onSubmit">查询</el-button>
	   </el-form-item>
   </el-form>
   
   <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"  class="demo-ruleForm">
	   <el-form-item label="密码" prop="pass">
		   <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
	   </el-form-item>
	   <el-form-item label="确认密码" prop="checkPass">
	   		   <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
	   </el-form-item>
	   <el-form-item label="年龄" prop="age">
	   		   <el-input v-model.number="ruleForm.age"></el-input>
	   </el-form-item>
	    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
		<el-button @click="resetForm('ruleForm')">重置</el-button>
   </el-form>
   
   
  </div>
</template>

<script>
const cityOptions=['上海','北京','广州','深圳'];	
export default {
	data() {
		var checkAge = (rule , value, callback) =>{
			if (!rule) {
				return callback(new Error('年龄不能为空'));
			}
			setTimeout(function() {
				if(!Number.isInteger(value)){
					callback(new Error('请输入数字'));
				}else{
					if(value < 18){
						callback(new Error('必须年满18岁'));
					}else{
						callback();
					}
				}
			}, 1000); 
		};
		var validatePass = (rule, value, callback)=>{
			if(value === ''){
				callback(new Error('请输入密码'));
			}else{
				if(this.ruleForm.checkPass !== ''){
					this.$refs.ruleForm.validateField('checkPass');
				}
				callback();
			}
		};
		var validatePass2 = (rule,value,callback)=>{
			if(value === ''){
				callback(new Error('请再次输入密码'));
			}else if(value !== this.ruleForm.pass){
				callback(new Error('两次输入的密码不一致'));
			}else{
				callback();
			}
		};
		const item = {
			date: '2016-02-01',
			name: 'xiaowang',
			address: '深圳市宝安区西乡街道'
		};
		return {
			radio:'1',
			checklist:['多选项2'],
			checkAll:false,
			checkedCities:[],
			cities:cityOptions,
			isIndetermainate:true,
			input:'',
			num:1,
			options:[{
				value:'选项1',
				label:'黄金糕'
			},{
				value:'选项2',
				label:'双皮奶'
			},{
				value:'选项3',
				label:'龙须面',
				disabled: true
			}],
			select:'',
			switchs:true,
			value:'100',
			tableData: Array(10).fill(item),
			fileList:[
				{name:'food.jpeg',url:'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'},
			    {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'},
				],
			form: {
				name:''
			},
			formInline: {
				user:'',
				region:''
			},
			ruleForm:{
				pass:'',
				checkPass:'',
				age:''
			},
			rules: {
				pass:[
					{validator: validatePass, trigger: 'blur'}
				],
				chekPass:[
					{validator: validatePass2, trigger: 'blur'}
				],
				age:[
					{validator: checkAge, trigger: 'blur'}
				]
			}
		};
	 },
  methods: {
    startHacking () {
      this.$notify({
        title: 'It works!',
        type: 'success',
        message: 'We\'ve laid the ground work for you. It\'s time for you to build something epic!',
        duration: 5000
      })
    },
	handleCheckAllChange(val){
		this.checkedCities = val ? cityOptions:[];
		this.isIndetermainate = false;
	},
	handleCheckCitiesChange(value){
		let checkedCount = value.length;
		this.checkAll = checkedCount === this.cities.length;
		this.isIndetermainate = checkedCount > 0 && checkedCount < this.cities.length;
	},
	handleChange(value){
		console.log("handleChange=="+value);
	},
	handleRemove(file,fileList){
		console.log("handleRemove=="+file.name,fileList);
	},
	handlePreview(file){
		console.log("handlePreview=点击已上传的文件"+file.name);
	},
	handleSuccess(response,file,fileList){
		console.log("handlePreview=点击已上传的文件"+file.name+'==');
	},
	handleExceed(files,fileList){
		this.$message.warning('当前限制选择3个文件');
	},
	beforeRemove(file,fileList){
		return this.$confirm(`确定移除 ${ file.name }？` );
	},
	onSubmit(){
		return this.$confirm(this.formInline.user+""+this.formInline.region+":确定要查询");
	},
	submitForm(ruleForm){
		this.$refs[ruleForm].validate((valid)=>{
			if(valid){
				alert('submit!');
			}else{
				console.log('error submit!');
				return false;
			}
		});
	},
	resetForm(formName){
	   this.$refs[formName].resetFields();	
	}
  }
}
</script>

<style>
/* #app {
  font-family: Helvetica, sans-serif;
  text-align: center;
}
 */
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
.el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 8px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.el-select .el-input{
	width: 130px;
}
.input-with-select{
	background-color: #fff;
}
</style>
