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
public class Main {
    Scanner scdata = new Scanner(System.in);
    public void menu(){
 
        int menu;  /* 入力したメニュー番号を格納 */
        initstock();/* 初期在庫データを入力 */
        while(true) {
            /* メニュー出力 */
            System.out.println("=============== menu ===============");
            System.out.println("1: Print all stock");
            System.out.println("2: Input Order");
            System.out.println("3: Add record");
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
    /* 初期在庫データの設定 */
    public void initstock(){
        SyohinData.setdata(0, 10, 2800, "SAKE", 20);
        SyohinData.setdata(1, 20, 3900, "WHISKY", 40);
        SyohinData.setdata(2, 30, 2500, "GIN", 15);
        SyohinData.setdata(3, 40, 6800, "BRANDY", 20);
        SyohinData.setdata(4, 50, 1900, "TEQUILA", 5 );
        SyohinData.setdata(5, 60, 2200, "RUM", 20);
        SyohinData.setdata(6, 70, 2600, "VODKA", 30);
        SyohinData.setdata(7, 80, 680,  "BEER", 50);
        SyohinData.setdata(8, 90, 2800, "SHEERY", 10);
        SyohinData.setdata(9, 100, 3500, "CHAMPAGNE", 30);
        SyohinData.setdata(10, 0, 0, "", 0);   
    }
    
    /* prtstock関数：在庫一覧を出力する  */
    public void prtstock()
    { 
        /* ヘッダ出力 */
        System.out.println("=========== all stock ===========");
        System.out.println("code  price       name    stock");
        System.out.println("---------------------------------");

        /* 在庫一覧出力 */
        for( int count = 0; SyohinData.sake[count].price!=0 ; count++ ) {
            System.out.printf("%4d %6d %10s %8d \n",  SyohinData.sake[count].code, 
    		 SyohinData.sake[count].price,  SyohinData.sake[count].name, SyohinData.sake[count].stock);
        }
    }

    /* iptorder関数：注文処理を行う関数  */
    public void iptorder()
    {
	int count;			/* 繰り返し処理用カウンタ */
	int code;			/* 商品コード */
	int kosuu;			/* 注文数 */
	int codechk = 0;            	/* 商品コードエラーチェック用 */
	char order;		/* 注文確認用 */

	/* コード、注文数入力 */
	System.out.print("please input code ... ");
	code = scdata.nextInt();
	System.out.print("please input kosuu ... ");
	kosuu = scdata.nextInt();

	/* 商品コードが正しいかチェック */
	for( count = 0; SyohinData.sake[count].price != 0; count++ ){   
            if(  SyohinData.sake[count].code == code ){
 		/* 商品コードが一致した場合 */
		codechk = 1;
               	break;
            }
	}

	/* 商品コードが商品テーブルのコードと一致しない場合 */
	if(codechk == 0){
            System.out.print("Code input Error !!\n");
            return;
	}

	/* 在庫数確認 */
	if(  SyohinData.sake[count].stock - kosuu >= 0 ){
            /* 在庫がある場合 */

            /* 発注数の確認 */
            System.out.println();
            System.out.println("|--------------------------|");
            System.out.printf("| shohin  | %10s     |\n",  SyohinData.sake[count].name);
            System.out.printf("|--------------------------|\n");
            System.out.printf("| kosuu   | %10d     |\n", kosuu);
            System.out.printf("|--------------------------|\n");
            System.out.printf("| tanka   | %10d yen |\n",  SyohinData.sake[count].price);
            System.out.println("|--------------------------|");
            System.out.printf("| goukei  | %10d yen |\n",  SyohinData.sake[count].price * kosuu);
            System.out.println("|--------------------------|\n");

            System.out.print(" \n Order? [ y / n ] ... ");
            for( ; ; ){
                order = scdata.next().charAt(0);
                if( order == 'y' || order == 'Y' ){
                    /* yもしくはYを入力すると、発注数分だけ在庫を減らす */
                    SyohinData.sake[count].stock -= kosuu;
                    System.out.println("done.");
                    break;
                }else if(order == 'n' || order == 'N'){
                            /* nを入力すると、発注処理を止める */
                            System.out.println("not yet");
                            break;
                    }
            }
	} else {
                   /* 在庫がない場合 */
                   System.out.printf("\n%S is empty.\n",  SyohinData.sake[count].name);
	}
    }
    
    public void add(){
        int record;
        
        System.out.print("Please input code price name stock...");
        for(record=0;SyohinData.sake[record].price!=0;record++);
        SyohinData.setdata(record, scdata.nextInt(), scdata.nextInt(),scdata.next(),scdata.nextInt());
    }
}

class SyohinData{
    static Syohin sake[] = new Syohin[101];
    static{
        for(int n=0; n < sake.length; n++){
            sake[n] = new Syohin();
        }
    }
    static void setdata(int rec, int data1, int data2, String str, int data3){
        sake[rec].code = data1;
        sake[rec].price = data2;
        sake[rec].name = str;
        sake[rec].stock = data3;
    }
}

class Syohin{
        int code;	/* 商品コード */
        int price;	/* 商品単価 */
        String name;	/* 商品名 */
        int stock;      /* 在庫数 */
}
