<template>
  <div class="dashboard-container">
    <component :is="currentRole" />
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import adminDashboard from './admin'
import editorDashboard from './editor'

export default {
  name: 'Dashboard',
  components: { adminDashboard, editorDashboard },
  data() {
    return {
      currentRole: 'adminDashboard'
    }
  },
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
  created() {
    // 原判断条件无法生效，roles不返回用户姓名，暂时修改判断条件
    // if (!this.roles.includes('admin')) {
    if (!this.roles.includes('ROLE_DEFAULT')) {
      this.currentRole = 'editorDashboard'
    }
  }
}
</script>
