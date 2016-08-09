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
 
        int menu;  /* ���͂������j���[�ԍ����i�[ */
        initstock();/* �����݌Ƀf�[�^����� */
        while(true) {
            /* ���j���[�o�� */
            System.out.println("=============== menu ===============");
            System.out.println("1: Print all stock");
            System.out.println("2: Input Order");
            System.out.println("3: Add record");
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
    /* �����݌Ƀf�[�^�̐ݒ� */
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
    
    /* prtstock�֐��F�݌Ɉꗗ���o�͂���  */
    public void prtstock()
    { 
        /* �w�b�_�o�� */
        System.out.println("=========== all stock ===========");
        System.out.println("code  price       name    stock");
        System.out.println("---------------------------------");

        /* �݌Ɉꗗ�o�� */
        for( int count = 0; SyohinData.sake[count].price!=0 ; count++ ) {
            System.out.printf("%4d %6d %10s %8d \n",  SyohinData.sake[count].code, 
    		 SyohinData.sake[count].price,  SyohinData.sake[count].name, SyohinData.sake[count].stock);
        }
    }

    /* iptorder�֐��F�����������s���֐�  */
    public void iptorder()
    {
	int count;			/* �J��Ԃ������p�J�E���^ */
	int code;			/* ���i�R�[�h */
	int kosuu;			/* ������ */
	int codechk = 0;            	/* ���i�R�[�h�G���[�`�F�b�N�p */
	char order;		/* �����m�F�p */

	/* �R�[�h�A���������� */
	System.out.print("please input code ... ");
	code = scdata.nextInt();
	System.out.print("please input kosuu ... ");
	kosuu = scdata.nextInt();

	/* ���i�R�[�h�����������`�F�b�N */
	for( count = 0; SyohinData.sake[count].price != 0; count++ ){   
            if(  SyohinData.sake[count].code == code ){
 		/* ���i�R�[�h����v�����ꍇ */
		codechk = 1;
               	break;
            }
	}

	/* ���i�R�[�h�����i�e�[�u���̃R�[�h�ƈ�v���Ȃ��ꍇ */
	if(codechk == 0){
            System.out.print("Code input Error !!\n");
            return;
	}

	/* �݌ɐ��m�F */
	if(  SyohinData.sake[count].stock - kosuu >= 0 ){
            /* �݌ɂ�����ꍇ */

            /* �������̊m�F */
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
                    /* y��������Y����͂���ƁA�������������݌ɂ����炷 */
                    SyohinData.sake[count].stock -= kosuu;
                    System.out.println("done.");
                    break;
                }else if(order == 'n' || order == 'N'){
                            /* n����͂���ƁA�����������~�߂� */
                            System.out.println("not yet");
                            break;
                    }
            }
	} else {
                   /* �݌ɂ��Ȃ��ꍇ */
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
        int code;	/* ���i�R�[�h */
        int price;	/* ���i�P�� */
        String name;	/* ���i�� */
        int stock;      /* �݌ɐ� */
}
