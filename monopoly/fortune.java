package monopoly;

class Card {
	public static int fortuneChance(int point, int Money, int otherPlayerMoney){
		String chance[] = {"�[�ۤv�`���B(�{��)��25%", "���Ǫ�35�U", "��o��L���a�`���B(�{��)��10%",
				"�i�H��ܥ[�\�@�ɩФl(�ݭn���)", "���Ǫ�15�U", "�d�Ǫ�20�U"};
		
		String fortune[] = {"���`���B(�{��)��25%", "���Ǫ�25�U", "�m�ܥ��@�����a���a",
				"�X�t��O5�U", "�i�H��ܤ@���w�����a�K�O�[�\�@�ɩФl", "�ð����A�@��10�U"};
		
		int cardpoint = (int)(Math.random()*6);
		
		int playersMoney = 0;
		int state = 0;

		
		if(point==9 || point==21) {
			System.out.println("��쪺���|�G" + chance[cardpoint]);
			
			switch(cardpoint) {
				case 0:
					playersMoney = (int)(Money*1.25);
					System.out.println("��o�{���G" + (int)(Money*0.25));
					return playersMoney;
				case 1:
					playersMoney = (int)(Money+35);
					System.out.println("��o�{���G35");
					return playersMoney;
				case 2:
					playersMoney = (int)(Money+otherPlayerMoney*0.1);
					System.out.println("��o�{���G" + (int)(otherPlayerMoney*0.1));
					return playersMoney;
				case 3:
					System.out.println();
					System.out.println("��ܭn�[�\���a(�i�H���[�\�A���[�\=0)�G");
					state = 1;
					return state;
				case 4:
					playersMoney = (int)(Money+15);
					System.out.println("��o�{���G15");
					return playersMoney;
				case 5:
					playersMoney = (int)(Money+20);
					System.out.println("��o�{���G20");
					return playersMoney;
			}
		}else if(point==3 || point==15) {
			System.out.println("��쪺�R�B�G" + fortune[cardpoint]);
			
			switch(cardpoint) {
				case 0:
					playersMoney = (int)Money - (int)(Money*0.25);
					System.out.println("���{���G" + (int)(Money*0.25));
					return playersMoney;
				case 1:
					playersMoney = (int)(Money+25);
					System.out.println("��o�{���G25");
					return playersMoney;
				case 2:
					System.out.println();
					System.out.println("��ܭn�m�ܪ��a(�i�H���m�ܡA���m��=0)�G");
					state = 2;
					return state;
				case 3:
					playersMoney = (int)(Money-5);
					System.out.println("�X�t�O�ΡG5");
					return playersMoney;
				case 4:
					System.out.println();
					System.out.println("��ܭn�[�\���a(�i�H���[�\�A���[�\=0)�G");
					state = 3;
					return state;
				case 5:
					playersMoney = (int)(Money-10);
					System.out.println("�@�ڡG10");
					return playersMoney;
			}
		}
		
		
		return playersMoney;
	}
}
