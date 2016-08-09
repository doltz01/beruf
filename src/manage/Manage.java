/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author ichiro
 */
public class Manage {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ManageMain mobj = new ManageMain();
       mobj.menu();
    }    
}

class ManageMain extends ZaikoMain{
     @Override
     public void menu(){/* Mainのmenuメソッドをオーバーライド */  
        int menu;       /* 入力したメニュー番号を格納 */
        readfile();     /* 初期在庫データのstock.txtからの読み込み */
        while(true) {
            /* メニュー出力 */
            System.out.println("=============== menu ===============");
            System.out.println("1: Print all stock");
            System.out.println("2: Input Order");
            System.out.println("3: Add record");
            System.out.println("4: Change record");
             System.out.println("5: Save Data");
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
                case  5:	/* 在庫データ保存 */
                   savefile();
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

    private void readfile(){
        int code;	/* 商品コード */
        int price;	/* 商品単価 */
        String name;	/* 商品名 */
        int stock;             /* 在庫数 */
        try{
            File infile = new File("stock.txt");
            Scanner fobj = new Scanner(infile);
            /* データ読み込み */
            for( int cnt = 0 ; fobj.hasNext(); cnt++ ){
                code = fobj.nextInt();
                price = fobj.nextInt();
                name = fobj.next();
                stock = fobj.nextInt();
                SyohinData.setdata(cnt, code, price, name, stock);
            }            
        }catch(IOException e){
            System.out.printf("File IO error:%s\n",e);
        }
    }
    public void savefile() {
            File outfile = new File("stock.txt");
            try(PrintWriter pwobj = new PrintWriter(new FileWriter(outfile))){
            	/* データ保存処理 */
	for(int cnt = 0; SyohinData.sake[cnt].code != 0; cnt++ ){
                        pwobj.printf("%3d %4d %10s %2d \r\n", SyohinData.sake[cnt].code, 
		SyohinData.sake[cnt].price, SyohinData.sake[cnt].name, SyohinData.sake[cnt].stock);
	}
	System.out.println("Save success!");    
            }catch(IOException e){
                 System.out.printf("File IO Error:%s", e);
            }
    }
}
