<template>
  <!-- 内容区域 -->
  <div class="bg-fa of">
    <section class="container">
      <div class="u-body mt40">
        <menu class="col-3 fl uMenu">
          <header class="comm-title"><h2 class="fl tac"><span class="c-333">个人中心</span></h2></header>

          <dl>
            <dd class="u-m-dd">
              <ul>
                <li>
                  <span>爷的资料</span>
                  <ol>
                    <li>
                      <nuxt-link :to="'/ucenter'" title="">
                        登录资料修改
                      </nuxt-link>
                    </li>
                  </ol>
                </li>
              </ul>
              <ul>
                <li>
                  <span>爷的动漫</span>
                  <ol>
                    <nuxt-link :to="'/ucenter/order'" tag="li" active-class="current" exact>
                      <a>爷的订单</a>
                    </nuxt-link>
                    <nuxt-link :to="'/ucenter/collect'" tag="li" active-class="current" exact>
                      <a>爷的收藏</a>
                    </nuxt-link>
                  </ol>
                </li>
              </ul>
            </dd>
          </dl>
        </menu>

        <nuxt-child />

        <div class="clear"/>
      </div>
    </section>
  </div>
  <!-- /内容区域 -->
</template>

<script>
export default {

}
</script>
