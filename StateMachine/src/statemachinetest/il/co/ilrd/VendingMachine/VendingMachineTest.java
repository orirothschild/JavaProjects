package il.co.ilrd.VendingMachine;

import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

import il.co.ilrd.VendingMachine.VendingMachine.iPrintable;

public class VendingMachineTest implements iPrintable{

	VendingMachine vm = null;
	Scanner myObj = null;
	iPrintable iprintobj = null;
	
	@Override
	public void print(String s){
		System.out.println(s);
	}
	@Before
	public void setUp() throws Exception {
		
		myObj = new Scanner(System.in);
		iprintobj = new VendingMachineTest();
		vm = new VendingMachine(iprintobj);
		vm.startMachine();
	}

	@Test
	public void testSelectProductScanner() {
		System.out.println("welcome,chose your action\n");
		while(vm.getEnable()) {
		
		System.out.println("1 to insert coins "+"2 to choose product "+ "3 to eject money" +" 4 to stop the machine");
		switch(myObj.nextInt()){
		case 1:
            System.out.println("please insert coins");
            vm.insertCoin(myObj.nextInt());
            break;
        case 2:
            System.out.println("choose product");
            vm.selectProduct(myObj.nextInt());
            break;
        case 3:
            System.out.println("your change is " + vm.ejectMoney());
            break;
        case 4:
        	System.out.println("byebye");
        	vm.Disable();
        	break;
        default:
            System.out.println("invalid input");
            break;
			}
		
		
		}


	}
}