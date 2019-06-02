package il.co.ilrd.VendingMachine;
import java.util.*;

//Override Thread.begintimestemp.run
public class VendingMachine{
    private static final int TIMEOUT = 5000;
    private int moneyInserted = 0;
    private volatile States currentState = States.INIT;
    private final List<Slot> products = new ArrayList<Slot>();
    private volatile boolean enable = true;
    private long begintimestemp = System.currentTimeMillis();
    private long finaltimestemp = System.currentTimeMillis() + 5000;
    private iPrintable printobj;
   
        
    public VendingMachine(iPrintable print) {
    	printobj = print;
    	Thread timoutThread = new Mythread();
    	fillList();
    	timoutThread.start();
    }
    
    private class Mythread extends Thread{ /*non static class knows who created it mythread created it*/
    @Override
    public void run() {
    	printobj.print("thread begin time stemped");
    	while(enable) {
    		try {
				Thread.sleep(TIMEOUT);
				begintimestemp = System.currentTimeMillis();
				currentState.timeOut(VendingMachine.this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
}
    
    /*vm useful applications*/
    public boolean getEnable() {return enable;}
    private int getMoney() {return moneyInserted;}
    private long timeLeft() {return finaltimestemp - begintimestemp;}
    private boolean compareTime() {
    	return finaltimestemp - begintimestemp > 0;
    }
    public void changeStatus(States newState, int settime) {
    	currentState = newState;
    	updateFinalTimeStemp(settime);
    }
    
    
    private void fillList() {
    	double i = 5;
    	int j = 6;
    	for(int k = 0; k < 10; ++k) {
    		Slot s = new Slot(++i,++j);
    		products.add(s);
    	}
    }
    
    private void updateFinalTimeStemp(int number) {
    	finaltimestemp = System.currentTimeMillis() + number;
    	begintimestemp = System.currentTimeMillis();
    }
    
    private void fillOnError() {
    	for(Slot sl: products) {
    		sl.setProdAmount(20);
    	}
    }
    
    private boolean isEmpty() {
    	int amountofprudacts = 0;
    	for(Slot sl: products) {
    		amountofprudacts += sl.getProdAmount();
    	}
    	return amountofprudacts == 0;
    }
    
    private enum States {//this object is the state and not the vm
        INIT {
            @Override
            void allOk(VendingMachine vm) {
                vm.changeStatus(IDLE, TIMEOUT);
            }
        },
        IDLE {
            @Override
            void insertCoin(VendingMachine vm, int amount) {
                vm.moneyInserted = amount;
                vm.changeStatus(COLLECT, TIMEOUT * 2);
            }
        },
        
        COLLECT {
            @Override
            int ejectMoney(VendingMachine vm) {
            	int returnvlaue = vm.getMoney();
                vm.printobj.print("money that was ejected \t" + vm.getMoney());
                vm.moneyInserted = 0;
                vm.changeStatus(IDLE, TIMEOUT);
                return returnvlaue;
            }
            @Override
            void insertCoin(VendingMachine vm, int amount) {
                vm.moneyInserted += amount;
                vm.changeStatus(COLLECT, TIMEOUT * 2);
            }
            @Override
            void selectSlot(VendingMachine vm, int idx) {
            	vm.changeStatus(VEND, TIMEOUT);
                vm.currentState.selectSlot(vm, idx);
            } 
        },
        VEND {
	        	@Override
	        	void selectSlot(VendingMachine vm, int idx) {
	        		Slot sl = vm.products.get(idx);
	        		if(!sl.isProdAvailable() || idx >= vm.products.size()) {
	        			vm.printobj.print("product is unavailable");
	        			errorVend(vm, idx);
	        		}
	        		else if(sl.getProdPrice() >= vm.getMoney()) {
	        			vm.printobj.print("not enough money");
	        			vm.changeStatus(COLLECT, TIMEOUT * 2);
	        		}
	        		else {
	        			sl.setProdAmount(-1);
	        			vm.moneyInserted -= sl.getProdPrice();
	        			vm.printobj.print("enjoy your product " + "your change is " + vm.getMoney());
	        			vm.changeStatus(TRANSACTION_COMPLETE, TIMEOUT);
	        		}
	        	}
	            void errorVend(VendingMachine vm, int idx) {
	            	@SuppressWarnings("unused")
					Slot sl = vm.products.get(idx);	            	
	        		if(vm.isEmpty()) {vm.fillOnError();}	        		
	        		vm.changeStatus(COLLECT, TIMEOUT * 2);
        	}	            
        },
        TRANSACTION_COMPLETE {
        	
        	@Override
        	 void timeOut(VendingMachine vm) {
             	try {       		
					Thread.sleep(vm.timeLeft());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
         		vm.ejectMoney();
         		vm.changeStatus(IDLE, TIMEOUT);
             }
        };
        //Events
        void insertCoin(VendingMachine vm, int amount) {
        	vm.printobj.print("cannot insert money yet");        
        	}
        
        void allOk(VendingMachine vm) {
        }
        
        void timeOut(VendingMachine vm) {
        	if(!vm.compareTime()) {
        		if(vm.currentState == COLLECT) {ejectMoney(vm);};
        		vm.changeStatus(IDLE, TIMEOUT);
        	}
        }
        
        void selectSlot(VendingMachine vm, int slot) {
            vm.printobj.print("you cannot chose a product yet \n");
        }
        
        int ejectMoney(VendingMachine vm) {
        	vm.printobj.print("cannot eject money yet \n");
        	return 0;
        }
        
    /*    void errorVend(VendingMachine vm, int idx) {
        }*/
    }
    // user functions//
    public void startMachine() {
    	currentState.allOk(this);
    }
    
    public void insertCoin(int amount) {
		currentState.insertCoin(this,amount);
	}
    
	public int ejectMoney() {
		return currentState.ejectMoney(this);
	}
	
	public void selectProduct(int idx) {
		currentState.selectSlot(this,idx);
	}
	public void Disable() {enable = false;}
    
	public interface iPrintable {
		void print(String s);

	}
	
     public static class Slot {
        private double prodPrice;
        private int prodAmount;
            
        Slot(double prodPrice, int prodAmount) {
            this.prodPrice = prodPrice;
            this.prodAmount = prodAmount;
        }
        
        private boolean isProdAvailable() {
            return prodAmount != 0;
        }
        
        private double getProdPrice() {
            return prodPrice;
        }
        
         private void setProdAmount(int Amount) {
            prodAmount += Amount;
      }
         private int getProdAmount() {
             return prodAmount;
       }
     }
}
