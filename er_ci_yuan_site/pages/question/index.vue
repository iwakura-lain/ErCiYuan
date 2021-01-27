<template>
  <div class="bg-fa of">
    <section class="container">
      <section class="i-question">
        <div class="fl col-7">
          <section class="mr30 pt10">
            <section class="c-infor-tabTitle c-tab-title">
              <a href="javascript: void(0)" title="全部问答" onclick="submitForm(0,'type')" class="current">全部问答</a>
              <a href="javascript: void(0)" title="课程问答" onclick="submitForm(1,'type')">课程问答</a>
              <a href="javascript: void(0)" title="学习分享" onclick="submitForm(2,'type')">学习分享</a>
            </section>
            <div class="js-wrap">
              <section class="fr">
                <span class="c-ccc"> <tt class="c-master f-fM">1</tt>/<tt class="c-666 f-fM">2</tt>
                </span>
              </section>
              <section class="fl">
                <ol class="js-tap clearfix">
                  <li class="current bg-orange"><a onclick="submitForm('addTime','order')" href="javascript:void(0)" title="最新">最新</a></li>
                  <li><a onclick="submitForm('replycount','order')" href="javascript:void(0)" title="热门">热门</a></li>
                  <li><a onclick="submitForm('status0','order')" href="javascript:void(0)" title="等待回答">等待回答</a></li>
                </ol>
              </section>
            </div>
            <!-- /问题列表 开始 -->
            <div class="q-list">
              <section class="q-all-list">
                <ul>
                  <li>
                    <aside class="q-head-pic">
                      <img src="~/assets/photo/customer/02.jpg" alt="">
                      <p class="hLh30 txtOf">
                        <span class="c-999"> goddess</span>
                      </p>
                    </aside>
                    <section class="q-txt-box">
                      <a class="replyBrowseNum" href="question/1" title="">
                        <div class="replyNum">
                          <span class="r-b-num">1</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">回答数</span>
                          </p>
                        </div>
                        <div class="browseNum">
                          <span class="r-b-num">93</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">浏览数</span>
                          </p>
                        </div>
                      </a>
                      <h3 class="hLh30 txtOf">
                        <em class="icon16 q-tw">&nbsp;</em>
                        <a href="question/1" title="" class="fsize16 c-333 vam">Premiere视频教学视频,大神分享下好吗</a>
                      </h3>
                      <h3 class="hLh30 txtOf mt5">
                        <em class="icon16 q-hd">&nbsp;</em>
                        <span class="fsize12 c-999 vam"> <tt class="c-green f-fM mr5">[最佳回答]</tt>
                          premiere是一款常用的视频编辑软件，由Adobe公司推出。现在常用的有CS4 CS5 CS6等版本。是一款编辑画面质量比较好的软件，有较好的兼容性，且可以与adobe公司推出的其他软件相互协作。目前这款软件广泛应用于广告制作和电视节目制作中。 其最新版本为Adobe Premiere Pro CC。</span>
                          <!-- 采纳最佳显示最佳答案内容 -->
                      </h3>
                      <div class="mt15">
                        <span class="c-ccc fl vam">2015-09-10 10:31</span>
                        <section class="fl ml20 pt10">
                          <div class="taglist clearfix">
                            <a title="Premiere" data-id="5" onclick="submitForm('5','questionsTagId')" class="list-tag" href="javascript:;">Premiere</a>
                          </div>
                        </section>
                        <div class="clear"/>
                      </div>
                    </section>
                  </li>
                  <li>
                    <aside class="q-head-pic">
                      <img src="~/assets/photo/customer/03.jpg" alt="">
                      <p class="hLh30 txtOf">
                        <span class="c-999"> 小少爷</span>
                      </p>
                    </aside>
                    <section class="q-txt-box">
                      <a class="replyBrowseNum" href="question/1" title="">
                        <div class="replyNum">
                          <span class="r-b-num">2</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">回答数</span>
                          </p>
                        </div>
                        <div class="browseNum">
                          <span class="r-b-num">22</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">浏览数</span>
                          </p>
                        </div>
                      </a>
                      <h3 class="hLh30 txtOf">
                        <em class="icon16 q-tw">&nbsp;</em>
                        <a href="question/1" title="" class="fsize16 c-333 vam">Web App开发教程,有没有分享下</a>
                      </h3>
                      <h3 class="hLh30 txtOf mt5">
                        <em class="icon16 q-hd">&nbsp;</em>
                        <span class="fsize12 c-999 vam"> <tt class="c-green f-fM mr5">[最佳回答]</tt>
                          支持</span>
                          <!-- 采纳最佳显示最佳答案内容 -->
                      </h3>
                      <div class="mt15">
                        <span class="c-ccc fl vam">2015-09-10 10:27</span>
                        <section class="fl ml20 pt10">
                          <div class="taglist clearfix">
                            <a title="WebApp" data-id="11" onclick="submitForm('11','questionsTagId')" class="list-tag" href="javascript:;">WebApp</a>
                          </div>
                        </section>
                        <div class="clear"/>
                      </div>
                    </section>
                  </li>
                  <li>
                    <aside class="q-head-pic">
                      <img src="~/assets/photo/customer/03.jpg" alt="">
                      <p class="hLh30 txtOf">
                        <span class="c-999"> 小少爷</span>
                      </p>
                    </aside>
                    <section class="q-txt-box">
                      <a class="replyBrowseNum" href="question/1" title="">
                        <div class="replyNum">
                          <span class="r-b-num">3</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">回答数</span>
                          </p>
                        </div>
                        <div class="browseNum">
                          <span class="r-b-num">10</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">浏览数</span>
                          </p>
                        </div>
                      </a>
                      <h3 class="hLh30 txtOf">
                        <em class="icon16 q-tw">&nbsp;</em>
                        <a href="question/1" title="" class="fsize16 c-333 vam">JavaScript刷新页面方法大全</a>
                      </h3>
                      <h3 class="hLh30 txtOf mt5">
                        <em class="icon16 q-hd">&nbsp;</em>
                        <span class="fsize12 c-999 vam"> <tt class="c-green f-fM mr5">[最佳回答]</tt>
                          我来点个赞</span>
                          <!-- 采纳最佳显示最佳答案内容 -->
                      </h3>
                      <div class="mt15">
                        <span class="c-ccc fl vam">2015-09-10 10:26</span>
                        <section class="fl ml20 pt10">
                          <div class="taglist clearfix">
                            <a title="JavaScript" data-id="10" onclick="submitForm('10','questionsTagId')" class="list-tag" href="javascript:;">JavaScript</a>
                          </div>
                        </section>
                        <div class="clear"/>
                      </div>
                    </section>
                  </li>
                  <li>
                    <aside class="q-head-pic">
                      <img src="~/assets/photo/customer/01.jpg" alt="">
                      <p class="hLh30 txtOf">
                        <span class="c-999"> Helen</span>
                      </p>
                    </aside>
                    <section class="q-txt-box">
                      <a class="replyBrowseNum" href="question/1" title="">
                        <div class="replyNum">
                          <span class="r-b-num">4</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">回答数</span>
                          </p>
                        </div>
                        <div class="browseNum">
                          <span class="r-b-num">106</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">浏览数</span>
                          </p>
                        </div>
                      </a>
                      <h3 class="hLh30 txtOf">
                        <em class="icon16 q-tw">&nbsp;</em>
                        <a href="question/1" title="" class="fsize16 c-333 vam">我是如何自学Android，资料分享（2015 版）</a>
                      </h3>
                      <h3 class="hLh30 txtOf mt5">
                        <em class="icon16 q-hd">&nbsp;</em>
                        <span class="fsize12 c-999 vam"> <tt class="c-ccc f-fM mr5">[最新回答]</tt> 分享的东西很全</span>
                        <!-- 有回答时显示最新一条的回答内容 -->
                      </h3>
                      <div class="mt15">
                        <span class="c-ccc fl vam">2015-09-10 10:16</span>
                        <section class="fl ml20 pt10">
                          <div class="taglist clearfix">
                            <a title="Android" data-id="8" onclick="submitForm('8','questionsTagId')" class="list-tag" href="javascript:;">Android</a>
                          </div>
                        </section>
                        <div class="clear"/>
                      </div>
                    </section>
                  </li>
                  <li>
                    <aside class="q-head-pic">
                      <img src="~/assets/photo/customer/01.jpg" alt="">
                      <p class="hLh30 txtOf">
                        <span class="c-999"> Helen</span>
                      </p>
                    </aside>
                    <section class="q-txt-box">
                      <a class="replyBrowseNum" href="question/1" title="">
                        <div class="replyNum">
                          <span class="r-b-num">1</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">回答数</span>
                          </p>
                        </div>
                        <div class="browseNum">
                          <span class="r-b-num">18</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">浏览数</span>
                          </p>
                        </div>
                      </a>
                      <h3 class="hLh30 txtOf">
                        <em class="icon16 q-tw">&nbsp;</em>
                        <a href="question/1" title="" class="fsize16 c-333 vam">我的MYSQL日志学习心得,给大家分享</a>
                      </h3>
                      <h3 class="hLh30 txtOf mt5">
                        <em class="icon16 q-hd">&nbsp;</em>
                        <span class="fsize12 c-999 vam"> <tt class="c-green f-fM mr5">[最佳回答]</tt>
                          不错</span>
                          <!-- 采纳最佳显示最佳答案内容 -->
                      </h3>
                      <div class="mt15">
                        <span class="c-ccc fl vam">2015-09-10 10:14</span>
                        <section class="fl ml20 pt10">
                          <div class="taglist clearfix">
                            <a title="Mysql" data-id="7" onclick="submitForm('7','questionsTagId')" class="list-tag" href="javascript:;">Mysql</a>
                          </div>
                        </section>
                        <div class="clear"/>
                      </div>
                    </section>
                  </li>
                  <li>
                    <aside class="q-head-pic">
                      <img src="~/assets/photo/customer/01.jpg" alt="">
                      <p class="hLh30 txtOf">
                        <span class="c-999"> Helen</span>
                      </p>
                    </aside>
                    <section class="q-txt-box">
                      <a class="replyBrowseNum" href="question/1" title="">
                        <div class="replyNum">
                          <span class="r-b-num">1</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">回答数</span>
                          </p>
                        </div>
                        <div class="browseNum">
                          <span class="r-b-num">12</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">浏览数</span>
                          </p>
                        </div>
                      </a>
                      <h3 class="hLh30 txtOf">
                        <em class="icon16 q-tw">&nbsp;</em>
                        <a href="question/1" title="" class="fsize16 c-333 vam">photoshop怎么抠图 ,能详细点吗</a>
                      </h3>
                      <h3 class="hLh30 txtOf mt5">
                        <em class="icon16 q-hd">&nbsp;</em>
                        <span class="fsize12 c-999 vam"> <tt class="c-green f-fM mr5">[最佳回答]</tt>
                          第一步，打开ps，打开你要修改的图片。文件---打开就可以了。
                          如果你并不追求完美的效果，而只是想快速一些，就选择快速选择工具，如图所示。用鼠标点击一下这里。
                          然后用鼠标左键在图片上想抠出来的区域拖动，直到选择好你想选择的区域之后，点击ctrl+c组合键复制。然后新建一个空白图片。在此空白图片上粘贴，就出现了你刚才抠出来的图片了。
                          如果你想得到更完美的抠图效果 ，可以使用铅笔工具 ，如图所示。
                          用钢笔工具在图像的边缘定出若二个点，如图所示，确定完成之后按crtl+回车键选择，然后复制，新建空白图片。
                          7在空白图片中粘贴，刚才抠出来的图便出现了。由于时间关系 ，我的抠图并不是十分准确。</span>
                          <!-- 采纳最佳显示最佳答案内容 -->
                      </h3>
                      <div class="mt15">
                        <span class="c-ccc fl vam">2015-09-10 10:11</span>
                        <section class="fl ml20 pt10">
                          <div class="taglist clearfix">
                            <a title="Photoshop" data-id="6" onclick="submitForm('6','questionsTagId')" class="list-tag" href="javascript:;">Photoshop</a>
                          </div>
                        </section>
                        <div class="clear"/>
                      </div>
                    </section>
                  </li>
                  <li>
                    <aside class="q-head-pic">
                      <img src="~/assets/photo/customer/01.jpg" alt="">
                      <p class="hLh30 txtOf">
                        <span class="c-999"> Helen</span>
                      </p>
                    </aside>
                    <section class="q-txt-box">
                      <a class="replyBrowseNum" href="question/1" title="">
                        <div class="replyNum">
                          <span class="r-b-num">0</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">回答数</span>
                          </p>
                        </div>
                        <div class="browseNum">
                          <span class="r-b-num">5</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">浏览数</span>
                          </p>
                        </div>
                      </a>
                      <h3 class="hLh30 txtOf">
                        <em class="icon16 q-tw">&nbsp;</em>
                        <a href="question/1" title="" class="fsize16 c-333 vam">jbpm5.4 执行TaskClient.start(taskId, userId, responseHandler);</a>
                      </h3>
                      <h3 class="hLh30 txtOf mt5">
                        <em class="icon16 q-hd">&nbsp;</em>
                        <span class="fsize12 c-999 vam">哈~~~ 此问题大家还有苦思冥想中...</span>
                        <!-- 没有回答时的内容 -->
                      </h3>
                      <div class="mt15">
                        <span class="c-ccc fl vam">2015-09-10 10:08</span>
                        <section class="fl ml20 pt10">
                          <div class="taglist clearfix">
                            <a title="JAVA" data-id="1" onclick="submitForm('1','questionsTagId')" class="list-tag" href="javascript:;">JAVA</a>
                          </div>
                        </section>
                        <div class="clear"/>
                      </div>
                    </section>
                  </li>
                  <li>
                    <aside class="q-head-pic">
                      <img src="~/assets/photo/customer/01.jpg" alt="">
                      <p class="hLh30 txtOf">
                        <span class="c-999">Helen</span>
                      </p>
                    </aside>
                    <section class="q-txt-box">
                      <a class="replyBrowseNum" href="question/1" title="">
                        <div class="replyNum">
                          <span class="r-b-num">1</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">回答数</span>
                          </p>
                        </div>
                        <div class="browseNum">
                          <span class="r-b-num">16</span>
                          <p class="hLh30">
                            <span class="c-999 f-fA">浏览数</span>
                          </p>
                        </div>
                      </a>
                      <h3 class="hLh30 txtOf">
                        <em class="icon16 q-tw">&nbsp;</em>
                        <a href="question/1" title="" class="fsize16 c-333 vam">谁知道支付宝即时到账接口中的有卡就能付没有了怎么回事</a>
                      </h3>
                      <h3 class="hLh30 txtOf mt5">
                        <em class="icon16 q-hd">&nbsp;</em>
                        <span class="fsize12 c-999 vam"> <tt class="c-green f-fM mr5">[最佳回答]</tt>
                          能不能详细的描述下</span>
                          <!-- 采纳最佳显示最佳答案内容 -->
                      </h3>
                      <div class="mt15">
                        <span class="c-ccc fl vam">2015-09-10 10:07</span>
                        <section class="fl ml20 pt10">
                          <div class="taglist clearfix">
                            <a title="Maya" data-id="2" onclick="submitForm('2','questionsTagId')" class="list-tag" href="javascript:;">Maya</a>
                          </div>
                        </section>
                        <div class="clear"/>
                      </div>
                    </section>
                  </li>
                </ul>
              </section>

              <!-- 公共分页 开始 -->
              <div>
                <div class="paging">
                  <a class="undisable" title>首</a>
                  <a id="backpage" class="undisable" href="#" title>&lt;</a>
                  <a href="#" title class="current undisable">1</a>
                  <a href="#" title>2</a>
                  <a id="nextpage" href="#" title>&gt;</a>
                  <a href="#" title>末</a>
                  <div class="clear"/>
                </div>
              </div>
              <!-- 公共分页 结束 -->
            </div>
            <!-- /问题列表 结束 -->
          </section>
        </div>
        <aside class="fl col-3">
          <div class="mt30 pl10">
            <section class="pt10">
              <a href="javascript:void(0)" onclick="toAddQuestions()" title="我要提问" class="comm-btn c-btn-5">我要提问</a>
            </section>
            <section class="pt20">
              <div class="taglist clearfix">
                <a onclick="submitForm('0','questionsTagId')" href="javascript:;" class="list-tag " data-id="0" title="JAVA">全部</a>
                <a title="JAVA" data-id="1" class="list-tag " href="javascript:;" onclick="submitForm('1','questionsTagId')">JAVA</a>
                <a title="Maya" data-id="2" class="list-tag " href="javascript:;" onclick="submitForm('2','questionsTagId')">Maya</a>
                <a title="MongoDB" data-id="3" class="list-tag " href="javascript:;" onclick="submitForm('3','questionsTagId')">MongoDB</a>
                <a title="Premiere" data-id="5" class="list-tag " href="javascript:;" onclick="submitForm('5','questionsTagId')">Premiere</a>
                <a title="Photoshop" data-id="6" class="list-tag " href="javascript:;" onclick="submitForm('6','questionsTagId')">Photoshop</a>
                <a title="Mysql" data-id="7" class="list-tag " href="javascript:;" onclick="submitForm('7','questionsTagId')">Mysql</a>
                <a title="Android" data-id="8" class="list-tag " href="javascript:;" onclick="submitForm('8','questionsTagId')">Android</a>
                <a title="Unity3D" data-id="9" class="list-tag " href="javascript:;" onclick="submitForm('9','questionsTagId')">Unity3D</a>
                <a title="JavaScript" data-id="10" class="list-tag " href="javascript:;" onclick="submitForm('10','questionsTagId')">JavaScript</a>
                <a title="WebApp" data-id="11" class="list-tag " href="javascript:;" onclick="submitForm('11','questionsTagId')">WebApp</a>
              </div>
            </section>
            <!-- /标签云 -->
            <section class="mt30">
              <section class="c-infor-tabTitle c-tab-title">
                <a href="javascript: void(0)" title="热门问答推荐">热门问答推荐</a>
              </section>
              <div class="q-r-rank-list">
                <ul id="hotQuestions"><li>	<aside class="q-r-r-num">		<div class="replyNum">			<span class="r-b-num">4</span>			<p class="hLh20">				<span class="c-999 f-fA">回答数</span>			</p>		</div>	</aside>	<h4 class="hLh30 txtOf">		<em class="icon16 q-tw">&nbsp;</em>		<a href="/questions/info/6" title="" class="fsize14 c-333 ml5">我是如何自学Android，资料分享（2015 版）</a>	</h4></li><li>	<aside class="q-r-r-num">		<div class="replyNum">			<span class="r-b-num">3</span>			<p class="hLh20">				<span class="c-999 f-fA">回答数</span>			</p>		</div>	</aside>	<h4 class="hLh30 txtOf">		<em class="icon16 q-tw">&nbsp;</em>		<a href="/questions/info/7" title="" class="fsize14 c-333 ml5">JavaScript刷新页面方法大全</a>	</h4></li><li>	<aside class="q-r-r-num">		<div class="replyNum">			<span class="r-b-num">2</span>			<p class="hLh20">				<span class="c-999 f-fA">回答数</span>			</p>		</div>	</aside>	<h4 class="hLh30 txtOf">		<em class="icon16 q-tw">&nbsp;</em>		<a href="/questions/info/8" title="" class="fsize14 c-333 ml5">Web App开发教程,有没有分享下</a>	</h4></li><li>	<aside class="q-r-r-num">		<div class="replyNum">			<span class="r-b-num">1</span>			<p class="hLh20">				<span class="c-999 f-fA">回答数</span>			</p>		</div>	</aside>	<h4 class="hLh30 txtOf">		<em class="icon16 q-tw">&nbsp;</em>		<a href="/questions/info/9" title="" class="fsize14 c-333 ml5">Premiere视频教学视频,大神分享下好吗</a>	</h4></li><li>	<aside class="q-r-r-num">		<div class="replyNum">			<span class="r-b-num">1</span>			<p class="hLh20">				<span class="c-999 f-fA">回答数</span>			</p>		</div>	</aside>	<h4 class="hLh30 txtOf">		<em class="icon16 q-tw">&nbsp;</em>		<a href="/questions/info/5" title="" class="fsize14 c-333 ml5">我的MYSQL日志学习心得,给大家分享</a>	</h4></li><li>	<aside class="q-r-r-num">		<div class="replyNum">			<span class="r-b-num">1</span>			<p class="hLh20">				<span class="c-999 f-fA">回答数</span>			</p>		</div>	</aside>	<h4 class="hLh30 txtOf">		<em class="icon16 q-tw">&nbsp;</em>		<a href="/questions/info/2" title="" class="fsize14 c-333 ml5">谁知道支付宝即时到账接口中的有卡就能付没有了怎么回事</a>	</h4></li><li>	<aside class="q-r-r-num">		<div class="replyNum">			<span class="r-b-num">1</span>			<p class="hLh20">				<span class="c-999 f-fA">回答数</span>			</p>		</div>	</aside>	<h4 class="hLh30 txtOf">		<em class="icon16 q-tw">&nbsp;</em>		<a href="/questions/info/4" title="" class="fsize14 c-333 ml5">photoshop怎么抠图 ,能详细点吗</a>	</h4></li><li>	<aside class="q-r-r-num">		<div class="replyNum">			<span class="r-b-num">1</span>			<p class="hLh20">				<span class="c-999 f-fA">回答数</span>			</p>		</div>	</aside>	<h4 class="hLh30 txtOf">		<em class="icon16 q-tw">&nbsp;</em>		<a href="/questions/info/1" title="" class="fsize14 c-333 ml5">视频在播放的时候视频会闪一下，但是视频无法正常播放。</a>	</h4></li></ul>
              </div>
            </section>
            <!-- /热门问答排行 -->
          </div>
        </aside>
        <div class="clear"/>
      </section>
    </section>
    <!-- /提问题 结束 -->
  </div>
</template>
