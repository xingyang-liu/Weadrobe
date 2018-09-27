package com.xiecc.Weadrobe.modules.main.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import com.xiecc.Weadrobe.R;
import com.xiecc.Weadrobe.base.BaseViewHolder;
import com.xiecc.Weadrobe.common.PLog;
import com.xiecc.Weadrobe.common.utils.SharedPreferenceUtil;
import com.xiecc.Weadrobe.common.utils.Util;
import com.xiecc.Weadrobe.component.AnimRecyclerViewAdapter;
import com.xiecc.Weadrobe.component.ImageLoader;
import com.xiecc.Weadrobe.modules.city.domain.Clothes;
import com.xiecc.Weadrobe.modules.main.domain.Weather;

public class ClothesAdapter extends AnimRecyclerViewAdapter<RecyclerView.ViewHolder> {
    private static String TAG = WeatherAdapter.class.getSimpleName();

    private Context mContext;

    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;

    private Weather mWeatherData;

    public ClothesAdapter(Weather weatherData) {
        this.mWeatherData = weatherData;
    }


    @Override
    public int getItemViewType(int position) {
        if (position ==ClothesAdapter.TYPE_ONE) {
            return ClothesAdapter.TYPE_ONE;
        }
        if (position == ClothesAdapter.TYPE_TWO) {
            return ClothesAdapter.TYPE_TWO;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        switch (viewType) {
            case TYPE_ONE:
                return new NowWeatherViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_temperature, parent, false));
            case TYPE_TWO:
                return new SuggestionViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_suggestion, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case TYPE_ONE:
                ((NowWeatherViewHolder) holder).bind(mWeatherData);
                break;
            case TYPE_TWO:
                ((SuggestionViewHolder) holder).bind(mWeatherData);
                break;
            default:
                break;
        }
        if (SharedPreferenceUtil.getInstance().getMainAnim()) {
            showItemAnim(holder.itemView, position);
        }
    }

    @Override
    public int getItemCount() {
        return mWeatherData.status != null ? 2 : 0;
    }

    /**
     * 当前天气情况
     */
    class NowWeatherViewHolder extends BaseViewHolder<Weather> {

        @Bind(R.id.weather_icon)
        ImageView weatherIcon;
        @Bind(R.id.temp_flu)
        TextView tempFlu;
        @Bind(R.id.temp_max)
        TextView tempMax;
        @Bind(R.id.temp_min)
        TextView tempMin;
        @Bind(R.id.temp_pm)
        TextView tempPm;
        @Bind(R.id.temp_quality)
        TextView tempQuality;
        @Bind(R.id.cardView)
        CardView cardView;

        NowWeatherViewHolder(View itemView) {
            super(itemView);
        }

        protected void bind(Weather weather) {
            try {

                tempFlu.setText(String.format("%s℃", weather.now.tmp));
                tempMax.setText(
                    String.format("↑ %s ℃", weather.dailyForecast.get(0).tmp.max));
                tempMin.setText(
                    String.format("↓ %s ℃", weather.dailyForecast.get(0).tmp.min));

                tempPm.setText(String.format("PM2.5: %s μg/m³", Util.safeText(weather.aqi.city.pm25)));
                tempQuality.setText(Util.safeText("空气质量： ", weather.aqi.city.qlty));
                ImageLoader.load(itemView.getContext(),
                    SharedPreferenceUtil.getInstance().getInt(weather.now.cond.txt, R.mipmap.none),
                    weatherIcon);
            } catch (Exception e) {
                PLog.e(TAG, e.toString());
            }
        }


    }

    /**
     * 当日建议
     */
    class SuggestionViewHolder extends BaseViewHolder<Weather> {
        @Bind(R.id.coat_brief)
        TextView coatBrief;
        @Bind(R.id.coat_txt)
        TextView coatTxt;
        @Bind(R.id.cloth_brief)
        TextView clothBrief;
        @Bind(R.id.cloth_txt)
        TextView clothTxt;
        @Bind(R.id.trousers_brief)
        TextView trousersBrief;
        @Bind(R.id.trousers_txt)
        TextView trousersTxt;
        @Bind(R.id.shoes_brief)
        TextView shoesBrief;
        @Bind(R.id.shoes_txt)
        TextView shoesTxt;
        @Bind(R.id.accessories_brief)
        TextView accessoriesBrief;
        @Bind(R.id.accessories_txt)
        TextView accessoriesTxt;

        SuggestionViewHolder(View itemView) {
            super(itemView);
        }

        protected void bind(Weather weather) {
            try {
                double temp = Integer.parseInt(weather.dailyForecast.get(0).tmp.min) * 0.25 + Integer.parseInt(mWeatherData.dailyForecast.get(0).tmp.max) * 0.75;
                int rain_rate = Integer.parseInt(weather.dailyForecast.get(0).pop);

                Clothes yes = new Clothes();

                Clothes fengyi = new Clothes();
                fengyi.setName("风衣");
                fengyi.ClothSort=22;
                fengyi.setKind(Clothes.COAT);
                fengyi.setWarmIndex(7);
                fengyi.setFormalIndex(2);
                fengyi.setIsRain(true);
                fengyi.setIntro("天气较凉，穿上风衣既帅气又防雨放风");

                Clothes toohot = new Clothes();
                toohot.setName("太热了不用穿了！");

                Clothes none = new Clothes();
                none.setName("无");

                Clothes tobuy =new Clothes();
                tobuy.setName("请在衣库中添加您拥有的衣物哦");

                Clothes yurongfu = new Clothes();
                yurongfu.setName("羽绒服");
                yurongfu.ClothSort=1;
                yurongfu.setKind(Clothes.COAT);
                yurongfu.setWarmIndex(10);
                yurongfu.setFormalIndex(2);
                yurongfu.setIntro("这么冷的天赶紧套件羽绒服，好看又保暖");

                Clothes maonidayi = new Clothes();
                maonidayi.setName("毛呢大衣");
                maonidayi.ClothSort=2;
                maonidayi.setKind(Clothes.COAT);
                maonidayi.setWarmIndex(7);
                maonidayi.setFormalIndex(2);
                maonidayi.setIntro("想不失风度地保持温度嘛？毛呢大衣适合你");

                Clothes bangqiufu = new Clothes();
                bangqiufu.setName("棒球服");
                bangqiufu.ClothSort=3;
                bangqiufu.setKind(Clothes.COAT);
                bangqiufu.setWarmIndex(7);
                bangqiufu.setFormalIndex(2);
                bangqiufu.setIntro("穿脱方便，既挡风又挡雨，年轻人的标配~~棒球服");

                Clothes weiyi = new Clothes();
                weiyi.setName("卫衣");
                weiyi.ClothSort=4;
                weiyi.setKind(Clothes.COAT);
                weiyi.setWarmIndex(5);
                weiyi.setFormalIndex(2);
                weiyi.setIntro("昼夜温差大怎么办？一件卫衣帮你解决一切困难！");

                Clothes jiake = new Clothes();
                jiake.setName("夹克");
                jiake.ClothSort=5;
                jiake.setKind(Clothes.COAT);
                jiake.setWarmIndex(5);
                jiake.setFormalIndex(2);
                jiake.setIntro("想走在时尚的前沿吗？夹克既保暖又时髦");

                Clothes xizhuang = new Clothes();
                xizhuang.setName("西装");
                xizhuang.ClothSort=6;
                xizhuang.setKind(Clothes.COAT);
                xizhuang.setWarmIndex(5);
                xizhuang.setFormalIndex(3);
                xizhuang.setIntro("参加正式场合的标配，一件西装hold住一切");

                Clothes chenshan = new Clothes();
                chenshan.setName("衬衫");
                chenshan.ClothSort=7;
                chenshan.setKind(Clothes.CLOTH);
                chenshan.setWarmIndex(2);
                chenshan.setFormalIndex(3);
                chenshan.setIntro("薄薄的衬衫，既透气又不失风度");

                Clothes txu = new Clothes();
                txu.setName("T恤");
                txu.ClothSort=8;
                txu.setKind(Clothes.CLOTH);
                txu.setWarmIndex(1);
                txu.setFormalIndex(2);
                txu.setIntro("户外运动的首选，吸汗又透气~");

                Clothes poloshan = new Clothes();
                poloshan.setName("polo衫");
                poloshan.ClothSort=9;
                poloshan.setKind(Clothes.CLOTH);
                poloshan.setWarmIndex(2);
                poloshan.setFormalIndex(2);
                poloshan.setIntro("天热参加活动就穿polo衫吧，绝对有品位");

                Clothes niuzaiku = new Clothes();
                niuzaiku.setName("牛仔裤");
                niuzaiku.ClothSort=10;
                niuzaiku.setKind(Clothes.PANTS);
                niuzaiku.setWarmIndex(5);
                niuzaiku.setFormalIndex(2);
                niuzaiku.setIntro("无论直筒还是修身，天冷还是天热，想显瘦就穿牛仔裤吧");

                Clothes xiuxianku = new Clothes();
                xiuxianku.setName("休闲裤");
                xiuxianku.ClothSort=11;
                xiuxianku.setKind(Clothes.PANTS);
                xiuxianku.setWarmIndex(4);
                xiuxianku.setFormalIndex(2);
                xiuxianku.setIntro("想偶尔换换裤子风格吗？各种休闲裤满足你的搭配需求");

                Clothes yundongku = new Clothes();
                yundongku.setName("运动裤");
                yundongku.ClothSort=23;
                yundongku.setKind(Clothes.PANTS);
                yundongku.setWarmIndex(4);
                yundongku.setFormalIndex(2);
                yundongku.setIntro("出去运动要穿运动裤哦");

                Clothes duanku = new Clothes();
                duanku.setName("短裤");
                duanku.ClothSort=12;
                duanku.setKind(Clothes.PANTS);
                duanku.setWarmIndex(2);
                duanku.setFormalIndex(2);
                duanku.setIntro("大热天穿长裤当心捂出问题，短裤配船袜透气又时尚");

                Clothes banxie = new Clothes();
                banxie.setName("板鞋");
                banxie.ClothSort=13;
                banxie.setKind(Clothes.SHOES);
                banxie.setWarmIndex(5);
                banxie.setFormalIndex(2);
                banxie.setIntro("我的滑板鞋时尚时尚最时尚");

                Clothes yundongxie = new Clothes();
                yundongxie.setName("运动鞋");
                yundongxie.ClothSort=14;
                yundongxie.setKind(Clothes.SHOES);
                yundongxie.setWarmIndex(5);
                yundongxie.setFormalIndex(2);
                yundongxie.setIntro("出门打球跑步一定要穿运动鞋哦");

                Clothes liangxie = new Clothes();
                liangxie.setName("凉鞋");
                liangxie.ClothSort=15;
                liangxie.setKind(Clothes.SHOES);
                liangxie.setWarmIndex(1);
                liangxie.setFormalIndex(1);
                liangxie.setIsRain(true);
                liangxie.setIntro("夏天容易出脚汗怎么办？凉鞋透气又凉快");

                Clothes renzituo = new Clothes();
                renzituo.setName("人字拖");
                renzituo.ClothSort=16;
                renzituo.setKind(Clothes.SHOES);
                renzituo.setWarmIndex(1);
                renzituo.setFormalIndex(1);
                renzituo.setIsRain(true);
                renzituo.setIntro("防水防热防出汗，随意中透出一丝霸气，没错就是它人字拖");

                Clothes pixue = new Clothes();
                pixue.setName("皮靴");
                pixue.ClothSort=17;
                pixue.setKind(Clothes.SHOES);
                pixue.setWarmIndex(7);
                pixue.setFormalIndex(2);
                pixue.setIntro("参加正式场合，西装配皮鞋，解决你的穿衣烦恼");

                Clothes kouzhao = new Clothes();
                kouzhao.setName("口罩");
                kouzhao.ClothSort=18;
                kouzhao.setKind(Clothes.ACCESSORY);
                kouzhao.setWarmIndex(0);
                kouzhao.setFormalIndex(1);
                kouzhao.setIntro("今天pm2.5有点高哦，记得戴口罩");

                Clothes mojing = new Clothes();
                mojing.setName("墨镜");
                mojing.ClothSort=19;
                mojing.setKind(Clothes.ACCESSORY);
                mojing.setWarmIndex(0);
                mojing.setFormalIndex(1);
                mojing.setIntro("哇今天阳光特别强，戴上墨镜很帅气");

                Clothes weijin = new Clothes();
                weijin.setName("围巾");
                weijin.ClothSort=20;
                weijin.setKind(Clothes.ACCESSORY);
                weijin.setWarmIndex(8);
                weijin.setFormalIndex(2);
                weijin.setIntro("天气较冷，戴上围巾能保护脖子哦");

                Clothes yusan = new Clothes();
                yusan.setName("雨伞");
                yusan.ClothSort=21;
                yusan.setKind(Clothes.ACCESSORY);
                yusan.setWarmIndex(0);
                yusan.setFormalIndex(2);
                yusan.setIsRain(true);
                yusan.setIntro("今天很可能会下雨，记得带上雨伞！");
                //////////////////
                //**************//
                //////////////////
                Clothes b_fengyi = new Clothes();
                b_fengyi.setName("风衣");
                b_fengyi.setIntro("建议您买一件风衣哦");


                Clothes b_yurongfu = new Clothes();
                b_yurongfu.setName("羽绒服");
                b_yurongfu.setIntro("建议您买一件羽绒服哦");

                Clothes b_maonidayi = new Clothes();
                b_maonidayi.setName("毛呢大衣");
                b_maonidayi.setIntro("建议您买一件毛呢大衣哦");

                Clothes b_bangqiufu = new Clothes();
                b_bangqiufu.setName("棒球服");
                b_bangqiufu.setIntro("建议您买一件棒球服哦");

                Clothes b_weiyi = new Clothes();
                b_weiyi.setName("卫衣");
                b_weiyi.setIntro("建议您买一件卫衣哦");

                Clothes b_jiake = new Clothes();
                b_jiake.setName("夹克");
                b_jiake.setIntro("建议您买一件夹克哦");

                Clothes b_xizhuang = new Clothes();
                b_xizhuang.setName("西装");
                b_xizhuang.setIntro("建议您买一件西装哦");

                Clothes b_chenshan = new Clothes();
                b_chenshan.setName("衬衫");
                b_chenshan.setIntro("建议您买一件衬衫哦");

                Clothes b_txu = new Clothes();
                b_txu.setName("T恤");
                b_txu.setIntro("建议您买一件T恤哦");

                Clothes b_poloshan = new Clothes();
                b_poloshan.setName("polo衫");
                b_poloshan.setIntro("建议您买一件polo衫哦");

                Clothes b_niuzaiku = new Clothes();
                b_niuzaiku.setName("牛仔裤");
                b_niuzaiku.setIntro("建议您买一件牛仔裤哦");

                Clothes b_xiuxianku = new Clothes();
                b_xiuxianku.setName("休闲裤");
                b_xiuxianku.setIntro("建议您买一件休闲裤哦");

                Clothes b_yundongku = new Clothes();
                b_yundongku.setName("运动裤");
                b_yundongku.setIntro("建议您买一件运动裤哦");

                Clothes b_duanku = new Clothes();
                b_duanku.setName("短裤");
                b_duanku.setIntro("建议您买一件短裤哦");

                Clothes b_banxie = new Clothes();
                b_banxie.setName("板鞋");
                b_banxie.setIntro("建议您买一双板鞋哦");

                Clothes b_yundongxie = new Clothes();
                b_yundongxie.setName("运动鞋");
                b_yundongxie.setIntro("建议您买一双运动鞋哦");

                Clothes b_liangxie = new Clothes();
                b_liangxie.setName("凉鞋");
                b_liangxie.setIntro("建议您买一双凉鞋哦");

                Clothes b_renzituo = new Clothes();
                b_renzituo.setName("人字拖");
                b_renzituo.setIntro("建议您买一双人字拖哦");

                Clothes b_pixue = new Clothes();
                b_pixue.setName("皮靴");
                b_pixue.setIntro("建议您买一双皮靴哦");

                Clothes b_kouzhao = new Clothes();
                b_kouzhao.setName("口罩");
                b_kouzhao.setIntro("建议您买一副口罩哦");

                Clothes b_mojing = new Clothes();
                b_mojing.setName("墨镜");
                b_mojing.setIntro("建议您买一副墨镜");

                Clothes b_weijin = new Clothes();
                b_weijin.setName("围巾");
                b_weijin.setIntro("建议您买一条围巾哦");

                Clothes b_yusan = new Clothes();
                b_yusan.setName("雨伞");
                b_yusan.setIntro("雨伞您总得买吧！");

                Clothes commend_coat = null;
                Clothes commend_cloth = null;
                Clothes commend_pants = null;
                Clothes commend_shoes = null;
                Clothes commend_accessory = new Clothes(none);


                if(rain_rate >= 50){
                    if(commend_coat.ishave[22]==true){
                    commend_coat = new Clothes(fengyi);
                    }else{
                        commend_coat=new Clothes(b_fengyi);
                    }
                    if(commend_coat.ishave[8]==true){
                    commend_cloth = new Clothes(txu);}else
                    {commend_cloth=new Clothes(b_txu);}
                    if(commend_coat.ishave[23]==true){
                    commend_pants = new Clothes(yundongku);}else
                    {commend_pants=new Clothes(b_yundongku);}
                    if(commend_coat.ishave[13]==true){
                    commend_shoes = new Clothes(banxie);}
                    else{commend_shoes=new Clothes(b_banxie);}
                    if(commend_coat.ishave[21]==true){
                    commend_accessory = new Clothes(yusan);}else
                    {commend_accessory = new Clothes(b_yusan);}
                }else{
                    if(temp<-5){
                        if(commend_coat.ishave[1]==true){
                        commend_coat = new Clothes(yurongfu);}else
                        {commend_coat = new Clothes(b_yurongfu);}
                        if(commend_coat.ishave[7]==true){
                        commend_cloth = new Clothes(chenshan);}
                        else{commend_cloth = new Clothes(b_chenshan);}
                        if(commend_coat.ishave[10]==true){
                        commend_pants = new Clothes(niuzaiku);}
                        else{commend_pants=new Clothes(b_niuzaiku);}
                        if(commend_coat.ishave[17]==true){
                        commend_shoes = new Clothes(pixue);}
                        else{commend_shoes=new Clothes(b_pixue);}
                        if(commend_coat.ishave[20]==true){
                            commend_accessory = new Clothes(weijin);}else
                        {commend_accessory = new Clothes(b_weijin);}
                    }
                    else if(temp>-5&&temp<5)
                     {
                         if(commend_coat.ishave[2]==true){
                             commend_coat = new Clothes(maonidayi);}else
                         {commend_coat = new Clothes(b_maonidayi);}
                         if(commend_coat.ishave[7]==true){
                             commend_cloth = new Clothes(chenshan);}
                         else{commend_cloth = new Clothes(b_chenshan);}
                         if(commend_coat.ishave[23]==true){
                             commend_pants = new Clothes(yundongku);}
                         else{commend_pants=new Clothes(b_yundongku);}
                         if(commend_coat.ishave[17]==true){
                             commend_shoes = new Clothes(pixue);}
                         else{commend_shoes=new Clothes(b_pixue);}
                         if(commend_coat.ishave[20]==true){
                             commend_accessory = new Clothes(weijin);}else
                         {commend_accessory = new Clothes(b_weijin);}
                     }


                    else if(temp>5&&temp<10){
                        if(commend_coat.ishave[3]==true){
                            commend_coat = new Clothes(bangqiufu);}else
                        {commend_coat = new Clothes(b_bangqiufu);}
                        if(commend_coat.ishave[9]==true){
                            commend_cloth = new Clothes(poloshan);}
                        else{commend_cloth = new Clothes(b_poloshan);}
                        if(commend_coat.ishave[11]==true){
                            commend_pants = new Clothes(xiuxianku);}
                        else{commend_pants=new Clothes(b_xiuxianku);}
                        if(commend_coat.ishave[13]==true){
                            commend_shoes = new Clothes(banxie);}
                        else{commend_shoes=new Clothes(b_banxie);}
                        if(commend_coat.ishave[19]==true){
                            commend_accessory = new Clothes(kouzhao);}else
                        {commend_accessory = new Clothes(b_kouzhao);}
                    }
                    else if(temp>10&&temp<15){
                        if(commend_coat.ishave[4]==true){
                            commend_coat = new Clothes(weiyi);}else
                        {commend_coat = new Clothes(b_weiyi);}
                        if(commend_coat.ishave[9]==true){
                            commend_cloth = new Clothes(poloshan);}
                        else{commend_cloth = new Clothes(b_poloshan);}
                        if(commend_coat.ishave[11]==true){
                            commend_pants = new Clothes(xiuxianku);}
                        else{commend_pants=new Clothes(b_xiuxianku);}
                        if(commend_coat.ishave[14]==true){
                            commend_shoes = new Clothes(yundongxie);}
                        else{commend_shoes=new Clothes(b_yundongxie);}
                        if(commend_coat.ishave[19]==true){
                            commend_accessory = new Clothes(kouzhao);}else
                        {commend_accessory = new Clothes(b_kouzhao);}
                    }
                    else if(temp>10&&temp<20){

                        if(commend_coat.ishave[5]==true){
                            commend_coat = new Clothes(jiake);}else
                        {commend_coat = new Clothes(b_jiake);}
                        if(commend_coat.ishave[8]==true){
                            commend_cloth = new Clothes(txu);}
                        else{commend_cloth = new Clothes(b_txu);}
                        if(commend_coat.ishave[11]==true){
                            commend_pants = new Clothes(xiuxianku);}
                        else{commend_pants=new Clothes(b_xiuxianku);}
                        if(commend_coat.ishave[14]==true){
                            commend_shoes = new Clothes(yundongxie);}
                        else{commend_shoes=new Clothes(b_yundongxie);}
                        if(commend_coat.ishave[19]==true){
                            commend_accessory = new Clothes(mojing);}else
                        {commend_accessory = new Clothes(b_mojing);}
                    }
                    else{
                        commend_coat = new Clothes(toohot);

                        if(commend_coat.ishave[8]){
                            commend_cloth = new Clothes(txu);}
                        else{commend_cloth = new Clothes(b_txu);}
                        if(commend_coat.ishave[12]==true){
                            commend_pants = new Clothes(duanku);}
                        else{commend_pants=new Clothes(b_duanku);}
                        if(commend_coat.ishave[15]==true){
                            commend_shoes = new Clothes(liangxie);}
                        else{commend_shoes=new Clothes(b_liangxie);}
                        if(commend_coat.ishave[19]==true){
                            commend_accessory = new Clothes(mojing);}else
                        {commend_accessory = new Clothes(b_mojing);}
                    }
                }


                coatBrief.setText(String.format("外套推荐---%s",commend_coat.getName()));
                coatTxt.setText(commend_coat.getIntro());

                clothBrief.setText(String.format("贴身衣物---%s", commend_cloth.getName()));
                clothTxt.setText(commend_cloth.getIntro());

                trousersBrief.setText(String.format("裤子推荐---%s", commend_pants.getName()));
                trousersTxt.setText(commend_pants.getIntro());

                shoesBrief.setText(String.format("鞋子推荐---%s", commend_shoes.getName()));
                shoesTxt.setText(commend_shoes.getIntro());

                accessoriesBrief.setText(String.format("配件推荐---%s", commend_accessory.getName()));
                accessoriesTxt.setText(commend_accessory.getIntro());
            } catch (Exception e) {
                PLog.e(e.toString());
            }
        }
    }

}
