package monopoly;

class Card {
	public static int fortuneChance(int point, int Money, int otherPlayerMoney){
		String chance[] = {"加自己總金額(現金)的25%", "獎學金35萬", "獲得其他玩家總金額(現金)的10%",
				"可以選擇加蓋一棟房子(需要花錢)", "獎學金15萬", "留學金20萬"};
		
		String fortune[] = {"扣總金額(現金)的25%", "獎學金25萬", "搶奪任一塊玩家的地",
				"出差花費5萬", "可以選擇一塊已有的地免費加蓋一棟房子", "亂停車，罰款10萬"};
		
		int cardpoint = (int)(Math.random()*6);
		
		int playersMoney = 0;
		int state = 0;

		
		if(point==9 || point==21) {
			System.out.println("抽到的機會：" + chance[cardpoint]);
			
			switch(cardpoint) {
				case 0:
					playersMoney = (int)(Money*1.25);
					System.out.println("獲得現金：" + (int)(Money*0.25));
					return playersMoney;
				case 1:
					playersMoney = (int)(Money+35);
					System.out.println("獲得現金：35");
					return playersMoney;
				case 2:
					playersMoney = (int)(Money+otherPlayerMoney*0.1);
					System.out.println("獲得現金：" + (int)(otherPlayerMoney*0.1));
					return playersMoney;
				case 3:
					System.out.println();
					System.out.println("選擇要加蓋的地(可以不加蓋，不加蓋=0)：");
					state = 1;
					return state;
				case 4:
					playersMoney = (int)(Money+15);
					System.out.println("獲得現金：15");
					return playersMoney;
				case 5:
					playersMoney = (int)(Money+20);
					System.out.println("獲得現金：20");
					return playersMoney;
			}
		}else if(point==3 || point==15) {
			System.out.println("抽到的命運：" + fortune[cardpoint]);
			
			switch(cardpoint) {
				case 0:
					playersMoney = (int)Money - (int)(Money*0.25);
					System.out.println("扣現金：" + (int)(Money*0.25));
					return playersMoney;
				case 1:
					playersMoney = (int)(Money+25);
					System.out.println("獲得現金：25");
					return playersMoney;
				case 2:
					System.out.println();
					System.out.println("選擇要搶奪的地(可以不搶奪，不搶奪=0)：");
					state = 2;
					return state;
				case 3:
					playersMoney = (int)(Money-5);
					System.out.println("出差費用：5");
					return playersMoney;
				case 4:
					System.out.println();
					System.out.println("選擇要加蓋的地(可以不加蓋，不加蓋=0)：");
					state = 3;
					return state;
				case 5:
					playersMoney = (int)(Money-10);
					System.out.println("罰款：10");
					return playersMoney;
			}
		}
		
		
		return playersMoney;
	}
}
