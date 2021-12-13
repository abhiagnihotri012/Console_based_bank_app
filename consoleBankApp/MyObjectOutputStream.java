package consoleBankApp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutputStream extends ObjectOutputStream{

	
	MyObjectOutputStream() throws IOException
    {
 
        // Super keyword refers to parent class instance
        super();
    }

    MyObjectOutputStream(OutputStream o) throws IOException
    {
        super(o);
    }
 
    public void writeStreamHeader() throws IOException
    {
        return;
    }

	public void reset(User user) {
		// TODO Auto-generated method stub
		
	}



}
