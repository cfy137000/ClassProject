package com.lanou.chenfengyao.chanyouji;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView mListView;
    private ImageView mMainHeader;
    private RelativeLayout mListViewHeader;
    private int mActionBarSize;
    private ListAdapter mListAdapter;
    private List<Bean> mBeans;
    private int mMinHeaderTranslation;
    private AccelerateDecelerateInterpolator mSmoothInterpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBarSize();
        initView();
    }

    private void initView() {
        mSmoothInterpolator = new AccelerateDecelerateInterpolator();
        mMainHeader = (ImageView) findViewById(R.id.id_main_header);
        mListView = (ListView) findViewById(R.id.id_list_view);
        mListViewHeader = (RelativeLayout) getLayoutInflater().inflate(R.layout.actionbar_item_header, null);
        mBeans = new ArrayList<>();
        Bean bean = new Bean();
        bean.setTxt1("《霍比特人》的故事大致发生在《魔戒》三部曲之前60年左右，讲述弗罗多的叔叔——“[1] ”比尔博·巴金斯（马丁·弗瑞曼 饰）的冒险历程。他被卷入了一场收回矮人的藏宝地孤山的旅程——这个地方被恶龙史矛革所占领着。由于灰袍巫师甘道夫（伊安·麦克莱恩 饰），比尔博出乎意料地加入了由13个矮人组成的冒险队伍中。他们要面对大量的哥布林、半兽人，致命的座狼骑士以及巨大的蜘蛛怪，变形者以及巫师……");
        bean.setTxt2("虽然这支队伍的目的地是孤山，但首先他们必须从哥布林隧道中逃出去。而在这里，比尔博遇见了改变他后半生的奇怪生物咕噜姆（安迪·瑟金斯 饰）。在一个地下湖的岸边，谦虚的比尔博不仅发现了自己的狡黠和勇气，也从咕噜姆那里得到了一枚戒指——就是这枚金色的简单的戒指，将会改变中土世界所有人的命运……我就知道彼得杰克逊不会让我失望，事实上他的作品也从来让人失望过，他的电影实现了无数普通人的梦，从《指环王》到《金刚》在到《丁丁历险记》，从不让失望，矮人王国的故事作为铺垫后 ，那段经典的配乐一如既往的强大，在听到与指环王三部曲一脉相承的音乐伴随着霍比特村的宁静甜美的风景，再次看到了熟悉的霍比特人村，感觉让我又一次看到了梦中的中土，还是那个村庄，还是那个全能的甘道夫，这次一群矮穷搓打高富丑的冒险旅程又一次开始了，随着剧情发展当那一曲《矮人之歌》响起时，低沉的声音，仿佛真的看到了矮人们的悲伤，那种发自内心的震撼，绝对会让人完全融入那梦的世界中，有个小发现矮人中的巴林在意外之旅中，就是那个矮人中的长者，作为一个在莫利亚战斗中的幸村者，也是索林故事的讲述者，但在《护戒使者》中，他已经成为了莫利亚坑洞的墓碑了，一束小小的光照耀着他的墓碑与灵柩，可以说这部电影都无时无刻不在提醒着我们这是《指环王》的前传，所以如果是没看过《指环王》的观众可能有少许的地方看不懂，随着剧情的发展在看到精灵王国那一幕的一瞬间我几乎无法呼吸了，差点就要哭了出来。天啊，那群老人都还在，他们一直在这里，瑞文戴尔，中土最后的庇护所，无法被时光改变的地方，十年时间，我们变大了，变老了，而瑞文戴尔的精灵们从未老去，不得不赞美那过人的电影特效， 走出了精灵王国，又见咕噜，这个在《指环王》中经典的形象，这回在意外之旅中的戏份少了很多，在此不作剧透，这回的敌对势力在《意外之旅》中以半兽人为主导，穿插着搞笑的食人妖，少了强大的魔多的黑暗君主反派感觉很不给力，不过最后恶龙的苏醒为第二部留下了很好的伏笔，值得期待，说完这些，我想说《霍比特人》还是那个《指环王》，经典的传奇依旧在延续着。");
        mBeans.add(bean);

        mListView.addHeaderView(mListViewHeader);
        mListAdapter = new ListAdapter(mBeans, this);
        mListView.setAdapter(mListAdapter);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                int scrollY = getScrollY();
             //   mMainHeader.setTranslationY(Math.max(-scrollY, mMinHeaderTranslation));
                Log.v("zgy", "======mMainHeader.getTranslationX()========" + mMainHeader.getTranslationY());
                float alpha = clamp(mMainHeader.getTranslationY() / mMinHeaderTranslation, 0.0f, 1.0f);
                Log.v("zgy", "======alpha========" + alpha);
                float actual = clamp(1.0f - mSmoothInterpolator.getInterpolation(alpha), 0.0f, 1.0f);
                mMainHeader.setAlpha(1.0f - actual);
            }
        });
        mMainHeader.post(new Runnable() {
            @Override
            public void run() {
                /*获取滚动的最小值*/
                mMinHeaderTranslation = -mMainHeader.getMeasuredHeight() + mActionBarSize;
                Log.v("zgy", "===========mMainHeader===" + mMainHeader.getMeasuredHeight());
            }
        });


        mMainHeader.setImageBitmap(FastBlur.doBlur(BitmapFactory.decodeResource(getResources(),R.drawable.img_header,new BitmapFactory.Options()),10));

    }


    /**
     * 这个方法还是比较有用的，
     *
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static float clamp(float value, float min, float max) {
        return Math.max(Math.min(value, max), min);
    }

    /**
     * 获取滚动的高度，用于检测是否需要滚动
     *
     * @return
     */
    private int getScrollY() {
        int scrollY = 0;
        int itemScrollY = 0;
        int itemNum = mListView.getFirstVisiblePosition();
        View firstVisible = mListView.getChildAt(0);
        if (firstVisible == null) {
            return scrollY;
        }
        if (itemNum >= 1) {
            itemScrollY = mListViewHeader.getMeasuredHeight();
        }
        scrollY = itemScrollY - firstVisible.getTop();
        return scrollY;
    }

    /**
     * 测量ActionBar的高度
     */
    private void getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
        mActionBarSize = TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
        Log.v("zgy", "=============actionBarSize=" + mActionBarSize);
    }
}
