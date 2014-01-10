//
//	This code was written by Ariel Dolan
//	Site	http://www.aridolan.com
//	Email	aridolan@netvision.net.il
//
//	You are welcome to do whatever you wish with this code, as long as you
//	add appropriate credits.
//

public class EfloyGeneration {

	long num;
	long steps;
	int time;
	int wid;
	int wfitness;
	int wenergy;
	int wsafety;
	int wcooperation;
	String wchrom;
	float wspeed;
	float wacc;
	float speedavg;
	float accavg;
	float dllavg;
	float dlsavg;
	float dslavg;
	float cllavg;
	float clsavg;
	float cslavg;


	public EfloyGeneration() {

		num = 0;
		steps = 0;
		time = 0;
		wid = 0;
		wfitness = 0;
		wenergy = 0;
		wsafety = 0;
		wcooperation = 0;
		wchrom = "";
		wspeed = 0;
		wacc = 0;
		speedavg = 0;
		accavg = 0;
		dllavg = 0;
		dlsavg = 0;
		dslavg = 0;
		cllavg = 0;
		clsavg = 0;
		cslavg = 0;

	}

	public EfloyGeneration(long Gnum, long Gsteps, int Gtime, int Wid,
		String Wchrom, int Wfit, int We, int Ws, int Wc, float Wspeed, float Wacc, 
		float Aspeed, float Aacc, float Adll, float Adls, float Adsl,
		float Acll, float Acls, float Acsl) {

		num = Gnum;
		steps = Gsteps;
		time = Gtime;
		wid = Wid;
		wfitness = Wfit;
		wenergy = We;
		wsafety = Ws;
		wcooperation = Wc;
		wchrom = Wchrom;
		wspeed = Wspeed;
		wacc = Wacc;
		speedavg = Aspeed;
		accavg = Aacc;
		dllavg = Adll;
		dlsavg = Adls;
		dslavg = Adsl;
		cllavg = Acll;
		clsavg = Acls;
		cslavg = Acsl;

	}

}