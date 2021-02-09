<template class="now">
  <div class="app-container">

    <el-input
      v-model="filterText"
      placeholder="搜索"/>
    <div style="margin-top: 20px">
      <el-button v-if="hasPerm('subject.add')" type="primary" size="mini" @click="openDialogByFirstSubject()">
        添加一级类目
      </el-button>
    </div>
    <el-dialog
      :visible.sync="dialogFormVisible"
      :title="saveStrOrUpdateStr"
      center
      width="300px"
    >
      <el-form :model="subject">
        <el-form-item :label-width="formLabelWidth" label="类别名称">
          <el-input v-model="subject.title" :placeholder="parentSubject.title"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="appendOrUpdate()">保存</el-button>
      </div>
    </el-dialog>

    <el-tree
      ref="tree"
      :data="subjectList"
      :props="defaultProps"
      :expand-on-click-node="false"
      :filter-node-method="filterNode"
      :indent="10"
      :default-expand-all="true"
      style="margin-top: 20px;"
    >
      <div slot-scope="{ node, data }" class="custom-tree-node">
        <el-tag v-if="data.parentId !== '0'" effect="dark" class="leaf-node" size="medium">{{ node.label }}</el-tag>
        <div v-if="data.parentId === '0'" class="leaf-node">{{ node.label }}</div>
        <div class="leaf-node">
          <el-button
            v-if="data.parentId === '0' && hasPerm('subject.update')"
            class="hasBackground"
            icon="el-icon-edit"
            type="warning"
            size="mini"
            @click="openDialog(data, 1)"
          />
          <el-button
            v-if="data.parentId === '0' && hasPerm('subject.add')"
            class="hasBackground"
            icon="el-icon-circle-plus"
            type="primary"
            size="mini"
            @click="openDialog(data)"
          />

          <el-button
            v-if="data.parentId !== '0' && hasPerm('subject.update')"
            icon="el-icon-edit"
            type="warning"
            size="mini"
            @click="openDialog(data)"/>
          <el-button
            v-if="data.parentId !== '0' && hasPerm('subject.remove')"
            icon="el-icon-delete"
            type="danger"
            size="mini"
            @click="remove(node, data)"/>
        </div>
      </div>
    </el-tree>
  </div>
</template>

<script>
import subjectApi from '@/api/subject'

export default {

  data() {
    return {
      filterText: '', // 过滤文本
      subjectList: [], // 数据列表
      defaultProps: {
        children: 'childrenSubject',
        label: 'title'
      },
      subject: {
        sort: 0
      },
      dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: '80px',
      parentSubject: {

      },
      saveStrOrUpdateStr: '',
      flag: 0 // 一级类目修改标志
    }
  },

  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },

  created() {
    this.fetchNodeList()
  },

  updated() {
    var content = document.getElementsByClassName('el-tree-node__content')
    var iconContent = document.getElementsByClassName('el-icon-caret-right')
    var childrenNode = document.getElementsByClassName('el-tree-node__children')
    for (var i = 0; i < content.length; i++) {
      var hasLeafNode = content[i].getElementsByClassName('hasBackground')
      if (hasLeafNode.length !== 0) {
        iconContent[i].style.color = 'black'
        content[i].style.color = 'black'
        content[i].style.backgroundImage = 'linear-gradient(to left, #fbc2eb 0%, #a6c1ee 100%)'
        childrenNode[i].style.backgroundImage = 'linear-gradient(to right, #a8edea 0%, #fed6e3 100%)'
      } else {
        content[i].style.background = ''
      }
    }
  },

  methods: {
    fetchNodeList() {
      subjectApi.getTreeList().then(response => {
        this.subjectList = response.data.items
      })
    },

    // 过滤节点
    filterNode(value, data) {
      if (!value) {
        return true
      }
      return data.title.toLowerCase().indexOf(value.toLowerCase()) !== -1
    },

    // 删除子结点
    remove(node, data) {
      // 询问是否删除
      this.$confirm('将删除该类别, 是否确定?', 'Warning', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        subjectApi.removeSubjectById(data.id).then(response => {
          this.fetchNodeList()
          // 弹出成功提示
          this.$notify({
            message: '<strong> <b style="color:green">删除成功<b><strong>',
            dangerouslyUseHTMLString: true,
            type: 'success'
          })
        })
      }).catch((err) => {
        if (err === 'cancel') {
          this.$notify({
            type: 'info',
            dangerouslyUseHTMLString: true,
            message: '<strong> <b style="color:#8A2BE2">取消删除<b> <strong>'
          })
        }
      })
    },

    // 新增子结点
    appendSubject() {
      this.subject.parentId = this.parentSubject.id
      subjectApi.addSubject(this.subject).then(response => {
        this.$notify({
          message: response.message,
          type: 'success'
        })
        // 刷新数据
        this.fetchNodeList()
      }).catch((err) => {
        this.$notify({
          type: 'info',
          dangerouslyUseHTMLString: true,
          message: err.message
        })
      })
      this.subject = {}
      this.dialogFormVisible = false
    },

    // 更新类目
    updateSubject() {
      this.subject.parentId = this.parentSubject.parentId
      this.subject.id = this.parentSubject.id
      subjectApi.updateSubject(this.subject).then(response => {
        this.$notify({
          message: response.message,
          type: 'success'
        })
        // 刷新数据
        this.fetchNodeList()
      }).catch((err) => {
        this.$notify({
          type: 'info',
          dangerouslyUseHTMLString: true,
          message: err.message
        })
      })
      this.subject = {}
      this.flag = 0
      this.dialogFormVisible = false
    },

    // 弹窗表格
    openDialog(data, flag) {
      this.dialogFormVisible = true
      this.parentSubject = data
      if (flag === 1 && this.parentSubject.parentId === '0') {
        this.saveStrOrUpdateStr = '修改一级类目'
        this.flag = 1
      } else if (this.parentSubject.parentId === '0') {
        this.saveStrOrUpdateStr = '新增类目'
      } else {
        this.saveStrOrUpdateStr = '修改类目'
      }
    },

    openDialogByFirstSubject() {
      this.dialogFormVisible = true
      this.saveStrOrUpdateStr = '新增一级类目'
      this.parentSubject.id = '0'
      // this.appendSubject()
    },

    // 增加 or 修改
    appendOrUpdate() {
      if (this.parentSubject.id === '0') {
        this.appendSubject()
        return
      }
      if (this.flag === 1 && this.parentSubject.parentId === '0') {
        this.updateSubject()
      } else if (this.parentSubject.parentId === '0') {
        this.appendSubject()
      } else {
        this.updateSubject()
      }
    }
  }
}
</script>

<style>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 15px;
  }
  .el-input--mini {
    padding-top: 25px;
  }
  .el-tree-node__content{
      margin-top: 10px;
  }
</style>
