(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d20ebb8"],{b12e:function(e,t,n){"use strict";n.r(t);var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("el-table",{staticStyle:{width:"100%"},attrs:{data:e.commentList.data,border:""}},[n("el-table-column",{attrs:{prop:"commentBlog.blogTitle",label:"标题","show-overflow-tooltip":""}}),n("el-table-column",{attrs:{prop:"commentUser.nickname",label:"评价人",width:"150"}}),n("el-table-column",{attrs:{prop:"commentContent",label:"评价内容",width:"400"}}),n("el-table-column",{attrs:{prop:"commentGood",label:"点赞数",width:"100"}}),n("el-table-column",{attrs:{prop:"createdTime",label:"评价时间",width:"200"}}),n("el-table-column",{attrs:{fixed:"right",label:"操作",width:"130"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{staticStyle:{"margin-top":"12px"},attrs:{type:"danger",round:"",size:"mini"},on:{click:function(n){return e.deleteById(t.row.id)}}},[e._v("删除")])]}}])})],1),n("el-pagination",{attrs:{"current-page":e.page.pageNUm,"page-sizes":[5,10,20,50],"page-size":e.page.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.commentList.totalCount},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)},o=[],l=n("b775"),i="comment",c={getPageBack:function(e){return Object(l["a"])({url:"/".concat(i,"/getPageBack"),method:"post",data:e})},delete:function(e){return Object(l["a"])({url:"/".concat(i,"/delete/").concat(e),method:"delete"})}},r={data:function(){return{page:{pageNum:1,pageSize:10},commentList:{}}},created:function(){this.getCommentList()},methods:{getCommentList:function(){var e=this;c.getPageBack(this.page).then((function(t){e.commentList=t.data}))},deleteById:function(e){var t=this;c.delete(e).then((function(e){t.$message.success(e.msg),t.getCommentList()}))},handleSizeChange:function(e){this.page.pageSize=e,this.getCommentList()},handleCurrentChange:function(e){this.page.pageNum=e,this.getCommentList()}}},s=r,u=n("2877"),m=Object(u["a"])(s,a,o,!1,null,null,null);t["default"]=m.exports}}]);