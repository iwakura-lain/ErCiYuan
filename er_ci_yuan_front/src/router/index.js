import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in subMenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if false, the item will hidden in breadcrumb(default is true)
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    // hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '次元管理局', icon: 'home' }
    }]
  },

  // 管理员管理
  {
    // 父路径
    path: '/admin',
    component: Layout,
    // 父路径默认跳转路径
    redirect: '/admin/list',
    name: 'admin',
    meta: { title: '魔法统筹协会', icon: 'adminHome', affix: true },
    children: [
      {
        // 子路径，完整路径为/父路径/子路径
        path: 'list',
        name: 'adminList',
        component: () => import('@/views/admin/adminList'),
        meta: { title: '在职魔女', icon: 'mohoxi', affix: true }
      },
      {
        path: 'create',
        name: 'adminCreate',
        component: () => import('@/views/admin/adminForm'),
        meta: { title: '注册魔女', icon: 'addAdmin', affix: true }
      },
      {
        path: 'edit/:id',
        name: 'adminEdit',
        component: () => import('@/views/admin/adminForm'),
        meta: { title: '魔女更新', affix: true },
        hidden: true
      }
    ]
  },

  // 动漫分类管理
  {
    // 父路径
    path: '/anime-type',
    component: Layout,
    // 父路径默认跳转路径
    redirect: '/anime-type/list',
    name: 'animeType',
    meta: { title: '动漫分类管理', icon: 'typeList', affix: true },
    children: [
      {
        // 子路径，完整路径为/父路径/子路径
        path: 'list',
        name: 'animeTypeList',
        component: () => import('@/views/animetype/animeTypelist'),
        meta: { title: '动漫分类列表', icon: 'animeTypeList', affix: true }
      },
      {
        path: 'import',
        name: 'adminTypeImport',
        component: () => import('@/views/animetype/importAnimeType'),
        meta: { title: '动漫分类导入', icon: 'import', affix: true }
      }
    ]
  },

  // 动漫管理
  {
    path: '/anime',
    component: Layout,
    redirect: '/anime/list',
    name: 'Anime',
    meta: { title: '动漫管理', icon: 'anime', affix: true },
    children: [
      {
        path: 'list',
        name: 'animeList',
        component: () => import('@/views/anime/animeList'),
        meta: { title: '动漫列表', icon: 'animeList', affix: true }
      },
      {
        path: 'info',
        name: 'animeInfo',
        component: () => import('@/views/anime/animeForm'),
        meta: { title: '发布动漫', icon: 'animePublish', affix: true }
      },
      {
        path: 'info/:id',
        name: 'animeInfoEdit',
        component: () => import('@/views/anime/animeForm'),
        meta: { title: '编辑动漫' },
        hidden: true
      },
      {
        path: 'chapter/:id',
        name: 'animeChapter',
        component: () => import('@/views/anime/animeForm'),
        meta: { title: '编辑目录' },
        hidden: true
      }
    ]
  },

  // 如果上面的路由都不匹配，则匹配 404
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
