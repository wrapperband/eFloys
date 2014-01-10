//
//	This code was written by Ariel Dolan
//	Site	http://www.aridolan.com
//	Email	aridolan@netvision.net.il
//
//	You are welcome to do whatever you wish with this code, as long as you
//	add appropriate credits.
//


class EfloyParam {

	float min;
	float max;
	float step;
	float value;
	int nsteps;
	String name;
	boolean mutatable;

	public EfloyParam (float mn, float mx, float st, float vl, String nm, boolean mt) {

		min = mn;
		max = mx;
		step = st;
		name = nm;
		value = vl;
		mutatable = mt;

		nsteps = (int) ((mx-mn)/step);
		//Efloys.deb.showMsg("Given step. name = "+nm+" mx= "+mx+" mn = "+mn+" step= "+step+" NSTEPS= "+nsteps);


	}

	public EfloyParam (float mn, float mx, int ns, float vl, String nm, boolean mt) {

		min = mn;
		max = mx;
		nsteps = ns;
		name = nm;
		value = vl;
		mutatable = mt;

		// ajd casst to float removed - step = (float) ((mx-mn)/nsteps);
                step = ((mx-mn)/nsteps);
		//Efloys.deb.showMsg("Given ns. name = "+nm+" mx= "+mx+" mn = "+mn+" nsteps= "+nsteps+" STEP= "+step);

	}

	char EncodeValue() {

		int n = (int) ((value - min)/step);
		char k = (char) (65+n);
		return k;

	}

	float DecodeValue(char kar) {

		int n;
		float k;
                
		try {
			n = (int) (kar - 65);
                        // ajd didnt use this, cast removen = (kar - 65);
			k = min + n*step;
			return k;
		}
		catch (Exception e) {
			// Efloys.deb.showMsg("DecodeValue. kar= "+kar+" n= "+n+" step = "+step+" min = "+min+" k= "+ k);
			// Efloys.deb.showMsg("DecodeValue. kar= "+kar+" e = "+e.toString());
			return 0f;
		}

	}

}

