package com.shj.shjtest.viewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shj.shjtest.R;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    ViewPager mViewPager;
    RelativeLayout mViewPagerBox;

    private ViewPagerAdapter1 mViewPagerAdapter1;
    private List<ImageView> mImageViewList;
    private  int imgs[]= new int[]{R.drawable.mm_title_btn_compose_normal,R.drawable.mm_title_btn_contact_normal,R.drawable.mm_title_btn_keyboard_normal};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager1);
        initData();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPagerBox = (RelativeLayout) findViewById(R.id.view_pager_box);

        mViewPager.setOffscreenPageLimit(10);

        mViewPagerAdapter1 = new ViewPagerAdapter1();
        mViewPager.setAdapter(mViewPagerAdapter1);
        mViewPager.setCurrentItem(1);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(TestActivity.this,"select"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //让整个页面都有滑动事件
//        mViewPagerBox.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return mViewPager.dispatchTouchEvent(event);
//            }
//        });

    }

    private void initData() {
        mImageViewList = new ArrayList<>();
        for(int i = 0;i<3;i++){
            ImageView iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.CENTER);
            iv.setImageResource(imgs[i]);
            mImageViewList.add(iv);
        }
    }

    class ViewPagerAdapter1 extends PagerAdapter{

        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
