<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 讲师列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">制作方</span>
        </h2>
      </header>
      <section class="c-sort-box unBr">
        <div>
          <!-- /无数据提示 开始-->
          <section v-if="items.length === 0" class="no-data-wrap">
            <img
              src="~/assets/img/noData.png"
              style="width:150px; height:150px; display: inline"
              class="img-responsive">
            <br>
            <span class="c-666 fsize14 ml10 vam">没有相关动漫</span>
          </section>
          <!-- /无数据提示 结束-->

          <!-- /数据列表 开始-->
          <article v-if="items.length !== 0" class="i-admin-list">
            <ul class="of">
              <li v-for="(item, index) in items" :key="index" >
                <section class="i-admin-wrap">
                  <div class="i-admin-pic">
                    <a :title="item.name" :href="'/maker/'+item.id">
                      <img :src="item.avatar" :alt="item.name" style="border-radius:0%" width="142">
                    </a>
                  </div>
                  <div class="mt10 hLh30 txtOf tac">
                    <a :title="item.name" :href="'/maker/'+item.id" class="fsize18 c-666">{{ item.name }}</a>
                  </div>
                  <div class="hLh30 txtOf tac">
                    <span class="fsize14 c-999" >{{ item.career }}</span>
                  </div>
                  <div class="mt15 i-q-txt">
                    <p class="c-999 f-fA">{{ item.intro }}</p>
                  </div>
                </section>
              </li>
            </ul>
            <div class="clear"/>
          </article>
          <!-- /数据列表 结束-->
        </div>
      </section>
    </section>
    <!-- /讲师列表 结束 -->
  </div>
</template>

<script>
import adminApi from '~/api/admin'

export default {

  asyncData() {
    return adminApi.list().then(response => {
      return {
        items: response.data.items
      }
    })
  }
}
</script>

<style>

</style>
