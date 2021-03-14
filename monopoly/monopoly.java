package monopoly;
import java.util.*;

public class monopoly {
	public static void main(String[] args) {
		
		//���a�H��
		int playernumber = 0;
		//���a�W��
		String pn[] = new String[5];
		//���a�{���ƶq
		int playerMoney[] = new int[5];
		//���a��m
		int playerLocation[] = new int[5];
		//���a�֦����g�a
		ArrayList playerBlock = new ArrayList();
				
		String [] block_name = {"Start", "�_��", "��j", "�R�B", "����", "�[�n", "����1"
				, "����1", "���v", "���|", "����", "�ʺ�", "����2", "����", "���s�j", "�R�B",
				"����", "���j", "����2", "����3", "�M�j", "���|", "�x�j"};
		
		int [] block_price = {0, 80, 90, 0, 40, 50, 0,
				0, 50, 0, 60, 0, 0,60, 70, 
				0, 70, 80, 0, 0, 90, 0, 100};
		//�ݩ���Ӫ��a���a
		int [] block_state = {0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0,0, 0, 
				0, 0, 0, 0, 0, 0, 0, 0};
		//�g�a���Ыε���
		int [] block_level = {0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0,0, 0, 
				0, 0, 0, 0, 0, 0, 0, 0};


		//���a�H��
		do {
			System.out.println("�п�J���a�H�ơG");
			
		    Scanner players = new Scanner(System.in);
		    playernumber = players.nextInt();
		    
		    if(playernumber<2 || playernumber>4) {
		    	
		    	System.out.println("��J���~�I");
		    	System.out.println();
		    	
		    }else {
		    	break;
		    }
		    
		}while(true);


		//�x�s���a�W�r
	    for(int i=1; i<=playernumber; i++) {
	    	
	    	System.out.println();
	    	System.out.println("�п�J���a"+ i +"���W�r�G");
	    	
	    	Scanner playername = new Scanner(System.in);
	    	pn[i] = playername.next();
	    	playerMoney[i] = 350;
	    	
	    	System.out.println("---------------------------------------------");
	    	System.out.println(i + " �����a�G" + pn[i]);	
	    	System.out.println(pn[i] + " ��o��l�{���G" + playerMoney[i]);
	    	System.out.println("---------------------------------------------");	
	    }

		
		System.out.println();
	    System.out.println();
	    System.out.println("=============================================");
	    System.out.println("                               �}�l�C��");
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
				
				//���a��F����m
				playerLocation[k] += point;
				
				//�������a���`���B
				for(int j=1; j<=playernumber; j++) {
					 MoneyTotal += playerMoney[j];
				}
				
				System.out.println(pn[k] + " �Y��l");
				System.out.println("��l�I�Ƭ� " + point + " �I");
				System.out.println();				
				
				//�P�_���a�O�_�]���@��
				if(playerLocation[k]>22) {
					
					countLocation = playerLocation[k] - 23;
					//System.out.println("countLocation = " + countLocation);	
					playerLocation[k] = 0;
					playerLocation[k] += countLocation;
					
					if(playerLocation[k] != 0) {
						
						System.out.println("�g�LStart�_�l�I�A��o40");
						System.out.println();
						playerMoney[k] +=40;
					}	
				}
				
				
				//��F���a�I
				System.out.println("��F "+ block_name[playerLocation[k]]);
				
				
				//�P�_�C�Ӧa�I�Ӱ�����
				if(playerLocation[k]==3 || playerLocation[k]==15) {
					
					System.out.println("��@�i�R�B ");
					System.out.println();
					
					state = Card.fortuneChance(playerLocation[k], playerMoney[k],
							MoneyTotal-playerMoney[k]);
					
					if(state>7) {
						playerMoney[k] = state;
					
					//�i�H�m�ܥ��@�����a���a---------------------------				
					}else if(state == 2){
						
						playerBlock.clear();
						
						for(int j=0; j<23; j++) {
							if( block_state[j] != k & block_state[j] != 0) {
								playerBlock.add(block_name[j] + "(" + j + ")");
							}
						}
						
						if(playerBlock.isEmpty() == true) {
							
							System.out.println("�S���i�H�m�ܪ��a ");
							
						}else {
							
							System.out.println("�i�m�ܪ��a��: " + playerBlock);
							System.out.println();
							System.out.println("�аݭn�m�ܪ��a���G");
							
							do {
								Scanner snatchBlock = new Scanner(System.in);
								int sB = snatchBlock.nextInt();
								
								if(block_state[sB] != k & block_state[sB] != 0) {
									
									System.out.println(pn[k] + " �m�ܤF " + pn[block_state[sB]] +
											" ���a " + block_name[sB]);
									block_state[sB] = k;
									break;									
								}else if(sB == 0){
									
									System.out.println("���m�ܥ���a�I");
									break;									
								}else {
									
									System.out.println("�Ӥg�a����Q�m�ܡI");
									System.out.println();
									System.out.println("�аݭn�m�ܪ��a���G");
								}
								
							}while(true);	
							
						}
					//-------------------------------------
						
					//�i�H��ܤ@���w�����a�K�O�[�\�@�ɩФl--------	
					}else if(state == 3){
						
						playerBlock.clear();
						
						for(int j=0; j<23; j++) {
							if(block_state[j] == k) {
								playerBlock.add(block_name[j] + "(" + j + ")");
							}
						}
						
						if(playerBlock.isEmpty() == true) {
							System.out.println("�S���i�H�[�\���a ");
						}else {
							System.out.println("�i�[�\���a��: " + playerBlock);
							System.out.println();
							System.out.println("�аݭn�[�\���a���G");
							
							do {
								Scanner addBlock = new Scanner(System.in);
								int aB = addBlock.nextInt();
								
								if(block_state[aB] == k) {
									
									if(block_level[aB] <=5) {
										
										block_level[aB] += 1;
										System.out.println();
										System.out.println(block_name[aB] + " �����Ťw�K�O�ɵ��� ��  " + 
												block_level[aB] + " �ŤF�I") ;
										break;
									}else {
										
										System.out.println();
										System.out.println(block_name[aB] + " �o���a���Ыε��Ťw�g�O�̰��ŤF�I");
										System.out.println("�п�ܨ�L�n�[�\���a�G");
									}
								}else if(aB == 0) {
									
									System.out.println("���[�\����a~~~");
									break;
								}else {
									
									System.out.println();
									System.out.println( block_name[aB] + " ���O�z���a�I");
									System.out.println("�п�ܨ�L�n�[�\���a�G");
								}
								
							}while(true);
						}
					}
					//-----------------------------------
					
				}else if(playerLocation[k]==9 || playerLocation[k]==21) {
					
					System.out.println("��@�i���|");
					System.out.println();
					state = Card.fortuneChance(playerLocation[k], playerMoney[k],
							MoneyTotal-playerMoney[k]);
					
					if(state>7) {
						
						playerMoney[k] = state;
					
					//�i�H��ܥ[�\�@�ɩФl(�ݭn���)----------
					}else if(state == 1){
						
						playerBlock.clear();
						
						for(int j=0; j<23; j++) {
							
							if(block_state[j] == k) {
								
								playerBlock.add(block_name[j] + "(" + j + ")");
							}
						}
						
						if(playerBlock.isEmpty() == true) {
							
							System.out.println("�S���i�H�[�\���a ");
						}else {
							
							System.out.println("�i�[�\���a��: " + playerBlock);
							System.out.println();
							System.out.println(pn[k] + " �ثe���{���G" + playerMoney[k] );
							System.out.println();
							System.out.println("�аݭn�[�\���a���G");
							
							do{
								Scanner addBlock = new Scanner(System.in);
								int aB = addBlock.nextInt();
								
								if(block_state[aB] == k) {
									
									if(block_level[aB] <=5) {
										
										playerMoney[k] -= block_price[aB];
										System.out.println("�[�\�O�ΡG "+ block_price[aB]);
										
										if(playerMoney[k]<=0) {
											
											System.out.println();
											System.out.println("=============================================");
											System.out.println(pn[k] + " �w�}���I");
											System.out.println("�C�������I");
											System.out.println("=============================================");
											System.exit(0);
											
										}else {
											
											block_level[aB] += 1;
											System.out.println();
											System.out.println(block_name[aB] + " �����Ťɵ��� �� " + 
													block_level[aB] + " ��") ;
											break;
										}
									}else {
										
										System.out.println();
										System.out.println(block_name[aB] + " �o���a���Ыε��Ťw�g�O�̰��ŤF�I");
										System.out.println("�п�ܨ�L�n�[�\���a�G");
									}	
								}else if(aB == 0) {
									
									System.out.println("���[�\����a~~~");
									break;
								}else {
									
									System.out.println();
									System.out.println( block_name[aB] + " ���O�z���a�I");
									System.out.println("�п�ܨ�L�n�[�\���a�G");
								}
								
							}while(true);
						}
					}
					//---------------------------------
					
				}else if(playerLocation[k]==6) {
					
					playerLocation[k] = 18;
					System.out.println("�f���e�� "+ block_name[playerLocation[k]]);					
				}else if(playerLocation[k]==18) {
					
					playerLocation[k] = 6;
					System.out.println("�f���e�� "+ block_name[playerLocation[k]]);					
				}else if(playerLocation[k]==7 || playerLocation[k]==12 || playerLocation[k]==19) {
					
					do {
						System.out.println();
						System.out.println("�аݭn��F������?(����1=7/����2=12/����3=19)");
						Scanner changeLocation = new Scanner(System.in);
						int cl = changeLocation.nextInt();
						
						if(cl == 7 || cl == 12 || cl == 19) {
							
							playerLocation[k] = cl;
							System.out.println();
							System.out.println("�f������F "+ block_name[playerLocation[k]]);
							break;
						}else {
							
							System.out.println("��J���~�I");
						}
					}while(true);
					
				}else if(playerLocation[k]==0 || playerLocation[k]==11) {
					
				}else {
					
					System.out.println("�a���G "+ block_price[playerLocation[k]]);
					System.out.println();
					System.out.println(pn[k] + " �ثe���{���G" + playerMoney[k] );
					System.out.println();
					
					do{
						if(block_state[playerLocation[k]] == 0) {
							
							System.out.println("�O�_�n�ʶR "+ block_name[playerLocation[k]] +
									" �o���a?(YES=1/NO=0)");
							
							Scanner buyBlock = new Scanner(System.in);
							int bB = buyBlock.nextInt();
							
							if(bB == 1) {
								
								System.out.println(pn[k] +"�ʶR�F "+ block_name[playerLocation[k]]
										+ " �o���a");
								playerMoney[k] -= block_price[playerLocation[k]];
								block_state[playerLocation[k]] = k;
								break;
							}else if(bB == 0) {
								
								System.out.println("���ʶR " + block_name[playerLocation[k]] + " �o���a");
								break;
							}else {
								
								System.out.println("��J���~�I");
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
								
								System.out.println(block_name[playerLocation[k]] + " �� " + 
										pn[block_state[playerLocation[k]]] + " ���g�a�A�Ыε��� = " +
										block_level[playerLocation[k]] + "�A����I�L���O " + toll);
								
								playerMoney[k] -= toll;
								playerMoney[block_state[playerLocation[k]]] += toll;
								
								break;
							}else if(block_state[playerLocation[k]] == k) {
								
								System.out.println("�O�_�n�[�\���g�a�H(YES=1/NO=0) ");
								Scanner addBlock = new Scanner(System.in);
								int aB = addBlock.nextInt();
								
								if(aB == 1) {
									if(block_level[playerLocation[k]] <5) {
										
										playerMoney[k] -= block_price[playerLocation[k]];
										System.out.println();
										System.out.println(pn[k] +" �[�\�F "+ block_name[playerLocation[k]]
												+ " �o���a");
										System.out.println("�[�\�O�ΡG "+ block_price[playerLocation[k]]);										
										
										if(playerMoney[k]<=0) {
											
											System.out.println();
											System.out.println("=============================================");
											System.out.println(pn[k] + " �w�}���I");
											System.out.println("�C�������I");
											System.out.println("=============================================");
											System.exit(0);											
										}else {
											
											block_level[playerLocation[k]] += 1;
											
											System.out.println();
											System.out.println(block_name[playerLocation[k]] + 
													" ���Ыε��Ťɵ��� �� " + block_level[playerLocation[k]] + " ��") ;
										}
										
									}else {
										
										System.out.println(block_name[playerLocation[k]] + 
												" �o���a���Ыε��Ťw�g�O�̰��ŤF�I");
									}
									
									break;
								}else if(aB == 0){
									
									System.out.println(pn[k] +" ���[�\  "+ block_name[playerLocation[k]]
											+ " �o���a");
									break;
								}else {
									
									System.out.println("��J���~�I");
								}
							}	
						}	
					}while(true);
				}
				
				if(playerMoney[k]<=0) {
					
					System.out.println();
					System.out.println("=============================================");
					System.out.println(pn[k] + " �w�}���I");
					System.out.println("�C�������I");
					System.out.println("=============================================");
					System.exit(0);
				}else {
					
					System.out.println();
					System.out.println(pn[k] + " ���l�B�G "+ playerMoney[k]);
					System.out.println("---------------------------------------------");
					System.out.println();
					System.out.println("---------------------------------------------");					
				}
	
			}
			
		}while(true);

	}
}