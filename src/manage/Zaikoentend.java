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
    public void menu(){ /* Main��menu���\�b�h���I�[�o�[���C�h */
  
        int menu;  /* ���͂������j���[�ԍ����i�[ */
        initstock();/* �����݌Ƀf�[�^����� */
        while(true) {
            /* ���j���[�o�� */
            System.out.println("=============== menu ===============");
            System.out.println("1: Print all stock");
            System.out.println("2: Input Order");
            System.out.println("3: Add record");
            System.out.println("4: Change record");
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
    /* �ύX���� */
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
