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
     public void menu(){/* Main��menu���\�b�h���I�[�o�[���C�h */  
        int menu;       /* ���͂������j���[�ԍ����i�[ */
        readfile();     /* �����݌Ƀf�[�^��stock.txt����̓ǂݍ��� */
        while(true) {
            /* ���j���[�o�� */
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
                case  1:	/* �݌Ɉꗗ�o�͏��� */
                   prtstock();
                   break;
                case  2:	/* �������� */
                   iptorder();
                   break;
                case  3:	/* �ǉ����� */
                   add();
                   break;
                case  4:	/* �ύX���� */
                   change();
                   break;
                case  5:	/* �݌Ƀf�[�^�ۑ� */
                   savefile();
                   break;
                case  9:	/* �݌ɊǗ��V�X�e���I�� */
                    System.out.println("Exit stock management system");
                    return;
                default:	/* ���j���[�ԍ����̓G���[ */
                    System.out.println("illegal number");
                    break;
            }
            /* ���̏����ɐi�ނ��߂̊m�F */
            System.out.print("\n Return [r] key ... ");
            while(!scdata.next().equals("r"));
        }
    }

    private void readfile(){
        int code;	/* ���i�R�[�h */
        int price;	/* ���i�P�� */
        String name;	/* ���i�� */
        int stock;             /* �݌ɐ� */
        try{
            File infile = new File("stock.txt");
            Scanner fobj = new Scanner(infile);
            /* �f�[�^�ǂݍ��� */
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
            	/* �f�[�^�ۑ����� */
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
