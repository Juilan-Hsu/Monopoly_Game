package monopoly;
import java.util.*;

public class monopoly {
	public static void main(String[] args) {
		
		//玩家人數
		int playernumber = 0;
		//玩家名稱
		String pn[] = new String[5];
		//玩家現金數量
		int playerMoney[] = new int[5];
		//玩家位置
		int playerLocation[] = new int[5];
		//玩家擁有的土地
		ArrayList playerBlock = new ArrayList();
				
		String [] block_name = {"Start", "北醫", "交大", "命運", "雲科", "暨南", "車站1"
				, "機場1", "彰師", "機會", "中興", "監獄", "機場2", "中正", "中山大", "命運",
				"高醫", "成大", "車站2", "機場3", "清大", "機會", "台大"};
		
		int [] block_price = {0, 80, 90, 0, 40, 50, 0,
				0, 50, 0, 60, 0, 0,60, 70, 
				0, 70, 80, 0, 0, 90, 0, 100};
		//屬於哪個玩家的地
		int [] block_state = {0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0,0, 0, 
				0, 0, 0, 0, 0, 0, 0, 0};
		//土地的房屋等級
		int [] block_level = {0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0,0, 0, 
				0, 0, 0, 0, 0, 0, 0, 0};


		//玩家人數
		do {
			System.out.println("請輸入玩家人數：");
			
		    Scanner players = new Scanner(System.in);
		    playernumber = players.nextInt();
		    
		    if(playernumber<2 || playernumber>4) {
		    	
		    	System.out.println("輸入錯誤！");
		    	System.out.println();
		    	
		    }else {
		    	break;
		    }
		    
		}while(true);


		//儲存玩家名字
	    for(int i=1; i<=playernumber; i++) {
	    	
	    	System.out.println();
	    	System.out.println("請輸入玩家"+ i +"的名字：");
	    	
	    	Scanner playername = new Scanner(System.in);
	    	pn[i] = playername.next();
	    	playerMoney[i] = 350;
	    	
	    	System.out.println("---------------------------------------------");
	    	System.out.println(i + " 號玩家：" + pn[i]);	
	    	System.out.println(pn[i] + " 獲得初始現金：" + playerMoney[i]);
	    	System.out.println("---------------------------------------------");	
	    }

		
		System.out.println();
	    System.out.println();
	    System.out.println("=============================================");
	    System.out.println("                               開始遊戲");
	    System.out.println("=============================================");
	    System.out.println();
	    System.out.println("---------------------------------------------");
		
		
		do {
			
			for(int k=1; k<=playernumber; k++) {
				
				int point = (int)(Math.random()*6+1);
				int state = 0;
				int MoneyTotal = 0;
				int countLocation = 0;
				int toll;
				
				//玩家到達的位置
				playerLocation[k] += point;
				
				//全部玩家的總金額
				for(int j=1; j<=playernumber; j++) {
					 MoneyTotal += playerMoney[j];
				}
				
				System.out.println(pn[k] + " 擲骰子");
				System.out.println("骰子點數為 " + point + " 點");
				System.out.println();				
				
				//判斷玩家是否跑完一圈
				if(playerLocation[k]>22) {
					
					countLocation = playerLocation[k] - 23;
					//System.out.println("countLocation = " + countLocation);	
					playerLocation[k] = 0;
					playerLocation[k] += countLocation;
					
					if(playerLocation[k] != 0) {
						
						System.out.println("經過Start起始點，獲得40");
						System.out.println();
						playerMoney[k] +=40;
					}	
				}
				
				
				//到達的地點
				System.out.println("抵達 "+ block_name[playerLocation[k]]);
				
				
				//判斷每個地點該做什麼
				if(playerLocation[k]==3 || playerLocation[k]==15) {
					
					System.out.println("抽一張命運 ");
					System.out.println();
					
					state = Card.fortuneChance(playerLocation[k], playerMoney[k],
							MoneyTotal-playerMoney[k]);
					
					if(state>7) {
						playerMoney[k] = state;
					
					//可以搶奪任一塊玩家的地---------------------------				
					}else if(state == 2){
						
						playerBlock.clear();
						
						for(int j=0; j<23; j++) {
							if( block_state[j] != k & block_state[j] != 0) {
								playerBlock.add(block_name[j] + "(" + j + ")");
							}
						}
						
						if(playerBlock.isEmpty() == true) {
							
							System.out.println("沒有可以搶奪的地 ");
							
						}else {
							
							System.out.println("可搶奪的地為: " + playerBlock);
							System.out.println();
							System.out.println("請問要搶奪的地為：");
							
							do {
								Scanner snatchBlock = new Scanner(System.in);
								int sB = snatchBlock.nextInt();
								
								if(block_state[sB] != k & block_state[sB] != 0) {
									
									System.out.println(pn[k] + " 搶奪了 " + pn[block_state[sB]] +
											" 的地 " + block_name[sB]);
									block_state[sB] = k;
									break;									
								}else if(sB == 0){
									
									System.out.println("不搶奪任何地！");
									break;									
								}else {
									
									System.out.println("該土地不能被搶奪！");
									System.out.println();
									System.out.println("請問要搶奪的地為：");
								}
								
							}while(true);	
							
						}
					//-------------------------------------
						
					//可以選擇一塊已有的地免費加蓋一棟房子--------	
					}else if(state == 3){
						
						playerBlock.clear();
						
						for(int j=0; j<23; j++) {
							if(block_state[j] == k) {
								playerBlock.add(block_name[j] + "(" + j + ")");
							}
						}
						
						if(playerBlock.isEmpty() == true) {
							System.out.println("沒有可以加蓋的地 ");
						}else {
							System.out.println("可加蓋的地為: " + playerBlock);
							System.out.println();
							System.out.println("請問要加蓋的地為：");
							
							do {
								Scanner addBlock = new Scanner(System.in);
								int aB = addBlock.nextInt();
								
								if(block_state[aB] == k) {
									
									if(block_level[aB] <=5) {
										
										block_level[aB] += 1;
										System.out.println();
										System.out.println(block_name[aB] + " 的等級已免費升等為 第  " + 
												block_level[aB] + " 級了！") ;
										break;
									}else {
										
										System.out.println();
										System.out.println(block_name[aB] + " 這塊地的房屋等級已經是最高級了！");
										System.out.println("請選擇其他要加蓋的地：");
									}
								}else if(aB == 0) {
									
									System.out.println("不加蓋任何地~~~");
									break;
								}else {
									
									System.out.println();
									System.out.println( block_name[aB] + " 不是您的地！");
									System.out.println("請選擇其他要加蓋的地：");
								}
								
							}while(true);
						}
					}
					//-----------------------------------
					
				}else if(playerLocation[k]==9 || playerLocation[k]==21) {
					
					System.out.println("抽一張機會");
					System.out.println();
					state = Card.fortuneChance(playerLocation[k], playerMoney[k],
							MoneyTotal-playerMoney[k]);
					
					if(state>7) {
						
						playerMoney[k] = state;
					
					//可以選擇加蓋一棟房子(需要花錢)----------
					}else if(state == 1){
						
						playerBlock.clear();
						
						for(int j=0; j<23; j++) {
							
							if(block_state[j] == k) {
								
								playerBlock.add(block_name[j] + "(" + j + ")");
							}
						}
						
						if(playerBlock.isEmpty() == true) {
							
							System.out.println("沒有可以加蓋的地 ");
						}else {
							
							System.out.println("可加蓋的地為: " + playerBlock);
							System.out.println();
							System.out.println(pn[k] + " 目前的現金：" + playerMoney[k] );
							System.out.println();
							System.out.println("請問要加蓋的地為：");
							
							do{
								Scanner addBlock = new Scanner(System.in);
								int aB = addBlock.nextInt();
								
								if(block_state[aB] == k) {
									
									if(block_level[aB] <=5) {
										
										playerMoney[k] -= block_price[aB];
										System.out.println("加蓋費用： "+ block_price[aB]);
										
										if(playerMoney[k]<=0) {
											
											System.out.println();
											System.out.println("=============================================");
											System.out.println(pn[k] + " 已破產！");
											System.out.println("遊戲結束！");
											System.out.println("=============================================");
											System.exit(0);
											
										}else {
											
											block_level[aB] += 1;
											System.out.println();
											System.out.println(block_name[aB] + " 的等級升等為 第 " + 
													block_level[aB] + " 級") ;
											break;
										}
									}else {
										
										System.out.println();
										System.out.println(block_name[aB] + " 這塊地的房屋等級已經是最高級了！");
										System.out.println("請選擇其他要加蓋的地：");
									}	
								}else if(aB == 0) {
									
									System.out.println("不加蓋任何地~~~");
									break;
								}else {
									
									System.out.println();
									System.out.println( block_name[aB] + " 不是您的地！");
									System.out.println("請選擇其他要加蓋的地：");
								}
								
							}while(true);
						}
					}
					//---------------------------------
					
				}else if(playerLocation[k]==6) {
					
					playerLocation[k] = 18;
					System.out.println("搭車前往 "+ block_name[playerLocation[k]]);					
				}else if(playerLocation[k]==18) {
					
					playerLocation[k] = 6;
					System.out.println("搭車前往 "+ block_name[playerLocation[k]]);					
				}else if(playerLocation[k]==7 || playerLocation[k]==12 || playerLocation[k]==19) {
					
					do {
						System.out.println();
						System.out.println("請問要抵達的機場?(機場1=7/機場2=12/機場3=19)");
						Scanner changeLocation = new Scanner(System.in);
						int cl = changeLocation.nextInt();
						
						if(cl == 7 || cl == 12 || cl == 19) {
							
							playerLocation[k] = cl;
							System.out.println();
							System.out.println("搭飛機抵達 "+ block_name[playerLocation[k]]);
							break;
						}else {
							
							System.out.println("輸入錯誤！");
						}
					}while(true);
					
				}else if(playerLocation[k]==0 || playerLocation[k]==11) {
					
				}else {
					
					System.out.println("地價： "+ block_price[playerLocation[k]]);
					System.out.println();
					System.out.println(pn[k] + " 目前的現金：" + playerMoney[k] );
					System.out.println();
					
					do{
						if(block_state[playerLocation[k]] == 0) {
							
							System.out.println("是否要購買 "+ block_name[playerLocation[k]] +
									" 這塊地?(YES=1/NO=0)");
							
							Scanner buyBlock = new Scanner(System.in);
							int bB = buyBlock.nextInt();
							
							if(bB == 1) {
								
								System.out.println(pn[k] +"購買了 "+ block_name[playerLocation[k]]
										+ " 這塊地");
								playerMoney[k] -= block_price[playerLocation[k]];
								block_state[playerLocation[k]] = k;
								break;
							}else if(bB == 0) {
								
								System.out.println("不購買 " + block_name[playerLocation[k]] + " 這塊地");
								break;
							}else {
								
								System.out.println("輸入錯誤！");
								System.out.println();
							}
						}else if(block_state[playerLocation[k]] >=1){
							
							toll = 0;
							
							if(block_state[playerLocation[k]] != k) {	
								
								if(block_level[playerLocation[k]] == 0) {
									
									toll = (int)(block_price[playerLocation[k]]*0.5);
								}else if(block_level[playerLocation[k]] == 1) {
									
									toll = (int)(block_price[playerLocation[k]]*0.6);
								}else if(block_level[playerLocation[k]] == 2) {
									
									toll = (int)(block_price[playerLocation[k]]*0.7);
								}else if(block_level[playerLocation[k]] == 3) {
									
									toll = (int)(block_price[playerLocation[k]]*0.8);
								}else if(block_level[playerLocation[k]] == 4) {
									
									toll = (int)(block_price[playerLocation[k]]*0.9);
								}else if(block_level[playerLocation[k]] == 5) {
									
									toll = block_price[playerLocation[k]];
								}																					
								
								System.out.println(block_name[playerLocation[k]] + " 為 " + 
										pn[block_state[playerLocation[k]]] + " 的土地，房屋等級 = " +
										block_level[playerLocation[k]] + "，須支付過路費 " + toll);
								
								playerMoney[k] -= toll;
								playerMoney[block_state[playerLocation[k]]] += toll;
								
								break;
							}else if(block_state[playerLocation[k]] == k) {
								
								System.out.println("是否要加蓋此土地？(YES=1/NO=0) ");
								Scanner addBlock = new Scanner(System.in);
								int aB = addBlock.nextInt();
								
								if(aB == 1) {
									if(block_level[playerLocation[k]] <5) {
										
										playerMoney[k] -= block_price[playerLocation[k]];
										System.out.println();
										System.out.println(pn[k] +" 加蓋了 "+ block_name[playerLocation[k]]
												+ " 這塊地");
										System.out.println("加蓋費用： "+ block_price[playerLocation[k]]);										
										
										if(playerMoney[k]<=0) {
											
											System.out.println();
											System.out.println("=============================================");
											System.out.println(pn[k] + " 已破產！");
											System.out.println("遊戲結束！");
											System.out.println("=============================================");
											System.exit(0);											
										}else {
											
											block_level[playerLocation[k]] += 1;
											
											System.out.println();
											System.out.println(block_name[playerLocation[k]] + 
													" 的房屋等級升等為 第 " + block_level[playerLocation[k]] + " 級") ;
										}
										
									}else {
										
										System.out.println(block_name[playerLocation[k]] + 
												" 這塊地的房屋等級已經是最高級了！");
									}
									
									break;
								}else if(aB == 0){
									
									System.out.println(pn[k] +" 不加蓋  "+ block_name[playerLocation[k]]
											+ " 這塊地");
									break;
								}else {
									
									System.out.println("輸入錯誤！");
								}
							}	
						}	
					}while(true);
				}
				
				if(playerMoney[k]<=0) {
					
					System.out.println();
					System.out.println("=============================================");
					System.out.println(pn[k] + " 已破產！");
					System.out.println("遊戲結束！");
					System.out.println("=============================================");
					System.exit(0);
				}else {
					
					System.out.println();
					System.out.println(pn[k] + " 的餘額： "+ playerMoney[k]);
					System.out.println("---------------------------------------------");
					System.out.println();
					System.out.println("---------------------------------------------");					
				}
	
			}
			
		}while(true);

	}
}