package com.xiecc.Weadrobe.modules.city.db;

import com.xiecc.Weadrobe.modules.city.domain.Clothes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 2015/9/30 0030.
 * 封装数据库操作
 */
public class ClothesDB {

    public ClothesDB() {

    }

    public static List<Clothes> loadSorts() {

        List<Clothes> list = new ArrayList<>();

        Clothes coat = new Clothes();
        coat.setKind(0);
        coat.setName("外套");
        coat.ClothSort = 30;
        list.add(coat);

        Clothes cloth = new Clothes();
        cloth.setKind(1);
        cloth.setName("贴身衣物");
        cloth.ClothSort = 30;
        list.add(cloth);

        Clothes trousers = new Clothes();
        trousers.setKind(2);
        trousers.setName("裤子");
        trousers.ClothSort = 30;
        list.add(trousers);

        Clothes shoes = new Clothes();
        shoes.setKind(3);
        shoes.setName("鞋子");
        shoes.ClothSort = 30;
        list.add(shoes);

        Clothes accessory = new Clothes();
        accessory.setKind(4);
        accessory.setName("配件");
        accessory.ClothSort = 30;
        list.add(accessory);

        return list;
    }

    public static List<Clothes> loadCities(int kind) {
        List<Clothes> list = new ArrayList<>();
        switch (kind){
            case 0:
                Clothes cloth0 = new Clothes();
                cloth0.setName("风衣");
                cloth0.setKind(cloth0.COAT);
                cloth0.ClothSort = 22;

                Clothes cloth1 = new Clothes();
                cloth1.setName("羽绒服");
                cloth1.setKind(cloth1.COAT);
                cloth1.ClothSort = 1;

                Clothes cloth2 = new Clothes();
                cloth2.setName("毛呢大衣");
                cloth2.setKind(cloth2.COAT);
                cloth2.ClothSort = 2;

                Clothes cloth3 = new Clothes();
                cloth3.setName("棒球服");
                cloth3.setKind(cloth3.COAT);
                cloth3.ClothSort = 3;

                Clothes cloth4 = new Clothes();
                cloth4.setName("卫衣");
                cloth4.setKind(cloth4.COAT);
                cloth4.ClothSort = 4;

                Clothes cloth5 = new Clothes();
                cloth5.setName("夹克");
                cloth5.setKind(cloth5.COAT);
                cloth5.ClothSort = 5;

                Clothes cloth6 = new Clothes();
                cloth6.setName("西装");
                cloth6.setKind(cloth6.COAT);
                cloth6.ClothSort = 6;

                list.add(cloth0);
                list.add(cloth1);
                list.add(cloth2);
                list.add(cloth3);
                list.add(cloth4);
                list.add(cloth5);
                list.add(cloth6);
                break;

            case 1:
                Clothes cloth7 = new Clothes();
                cloth7.setName("衬衫");
                cloth7.setKind(cloth7.CLOTH);
                cloth7.ClothSort = 7;

                Clothes cloth8 = new Clothes();
                cloth8.setName("T恤");
                cloth8.setKind(cloth8.CLOTH);
                cloth8.ClothSort = 8;

                Clothes cloth9 = new Clothes();
                cloth9.setName("polo衫");
                cloth9.setKind(cloth8.CLOTH);
                cloth9.ClothSort = 9;

                list.add(cloth7);
                list.add(cloth8);
                list.add(cloth9);
                break;

            case 2:
                Clothes cloth10 = new Clothes();
                cloth10.setName("牛仔裤");
                cloth10.setKind(cloth10.PANTS);
                cloth10.ClothSort = 10;

                Clothes cloth11 = new Clothes();
                cloth11.setName("休闲裤");
                cloth11.setKind(cloth11.PANTS);
                cloth11.ClothSort = 11;

                Clothes cloth12 = new Clothes();
                cloth12.setName("运动裤");
                cloth12.setKind(cloth12.PANTS);
                cloth12.ClothSort = 23;

                Clothes cloth13 = new Clothes();
                cloth13.setName("短裤");
                cloth13.setKind(cloth13.PANTS);
                cloth13.ClothSort = 12;

                list.add(cloth10);
                list.add(cloth11);
                list.add(cloth12);
                list.add(cloth13);
                break;

            case 3:
                Clothes cloth14 = new Clothes();
                cloth14.setName("板鞋");
                cloth14.setKind(cloth14.SHOES);
                cloth14.ClothSort = 13;

                Clothes cloth15 = new Clothes();
                cloth15.setName("运动鞋");
                cloth15.setKind(cloth15.SHOES);
                cloth15.ClothSort = 14;

                Clothes cloth16 = new Clothes();
                cloth16.setName("凉鞋");
                cloth16.setKind(cloth16.SHOES);
                cloth16.ClothSort = 15;

                Clothes cloth17 = new Clothes();
                cloth17.setName("人字拖");
                cloth17.setKind(cloth17.SHOES);
                cloth17.ClothSort = 16;

                Clothes cloth18 = new Clothes();
                cloth18.setName("皮靴");
                cloth18.setKind(cloth18.SHOES);
                cloth18.ClothSort = 17;

                list.add(cloth14);
                list.add(cloth15);
                list.add(cloth16);
                list.add(cloth17);
                list.add(cloth18);
                break;

            case 4:
                Clothes cloth19 = new Clothes();
                cloth19.setName("口罩");
                cloth19.setKind(cloth19.ACCESSORY);
                cloth19.ClothSort = 18;

                Clothes cloth20 = new Clothes();
                cloth20.setName("墨镜");
                cloth20.setKind(cloth20.ACCESSORY);
                cloth20.ClothSort = 19;

                Clothes cloth21 = new Clothes();
                cloth21.setName("围巾");
                cloth21.setKind(cloth21.ACCESSORY);
                cloth21.ClothSort = 20;

                Clothes cloth22 = new Clothes();
                cloth22.setName("雨伞");
                cloth22.setKind(cloth22.ACCESSORY);
                cloth22.ClothSort = 21;

                list.add(cloth19);
                list.add(cloth20);
                list.add(cloth21);
                list.add(cloth22);
                break;
        }
        return list;
    }


}
