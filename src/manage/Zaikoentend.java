/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import java.util.Scanner;

/**
 *
 * @author ichiro
 */
class Zaikoextend {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ZaikoMain mobj = new ZaikoMain();
        mobj.menu();
    }
}
 class ZaikoMain extends Main{
    Scanner scdata = new Scanner(System.in);
    public void menu(){ /* Mainのmenuメソッドをオーバーライド */
  
        int menu;  /* 入力したメニュー番号を格納 */
        initstock();/* 初期在庫データを入力 */
        while(true) {
            /* メニュー出力 */
            System.out.println("=============== menu ===============");
            System.out.println("1: Print all stock");
            System.out.println("2: Input Order");
            System.out.println("3: Add record");
            System.out.println("4: Change record");
            System.out.println("9: Quit");
            System.out.print("Input number...");
            menu = scdata.nextInt();

            switch( menu ) {
                case  1:	/* 在庫一覧出力処理 */
                   prtstock();
                   break;
                case  2:	/* 注文処理 */
                   iptorder();
                   break;
                case  3:	/* 追加処理 */
                   add();
                   break;
                case  4:	/* 変更処理 */
                   change();
                   break;
                case  9:	/* 在庫管理システム終了 */
                    System.out.println("Exit stock management system");
                    return;
                default:	/* メニュー番号入力エラー */
                    System.out.println("illegal number");
                    break;
            }
            /* 次の処理に進むための確認 */
            System.out.print("\n Return [r] key ... ");
            while(!scdata.next().equals("r"));
        }
    }
    /* 変更処理 */
    @SuppressWarnings("empty-statement")
    void change(){
        int record;
        int code;
        
        System.out.print("Please input code price name stock...");
        code = scdata.nextInt();
        for(record=0;code != SyohinData.sake[record].code;record++);
        SyohinData.setdata(record, code, scdata.nextInt(),scdata.next(),scdata.nextInt());    
    }     
}
