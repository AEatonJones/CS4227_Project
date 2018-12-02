package extras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExtraVisitor implements Visitor {

    @Override
    public String getStats()
    {
        //Read through file, create appropriate extraRoom objects, use visit to keep running tally, print string of details
        int gymHours = 0;
        int spaHours = 0;
        int poolHours = 0;
        int functionHours = 0;
        int conferenceHours = 0;
        int swimmingPoolHours = 0;
        
        double gymIncome = 0.0;
        double spaIncome = 0.0;
        double poolIncome = 0.0;
        double functionIncome = 0.0;
        double conferenceIncome = 0.0;
        double swimmingPoolIncome = 0.0;
        
        try(BufferedReader reader = new BufferedReader(new FileReader("src/resources/extras.txt")))
        {
            String line;
            while((line = reader.readLine()) != null)
            {
                String [] details = line.split(",");
                int hours = Integer.parseInt(details[1]);
                                
                switch(details[0])
                {
                    case "Gym": Gym gym = new Gym(hours); 
                                gymIncome += visit(gym);
                                gymHours += hours;
                                break;
                                
                    case "Spa": Spa spa = new Spa(hours); 
                                spaIncome += visit(spa);
                                spaHours += hours;
                                break;
                    
                    case "Pool": PoolRoom pool = new PoolRoom(hours); 
                                 poolIncome += visit(pool);
                                 poolHours += hours;
                                 break;

                    case "Function": FunctionRoom function = new FunctionRoom(hours); 
                                     functionIncome += visit(function);
                                     functionHours += hours;
                                     break;

                    case "Conference": ConferenceRoom conference = new ConferenceRoom(hours); 
                                       conferenceIncome += visit(conference);
                                       conferenceHours += hours;
                                       break;
                                
                    case "Swimming Pool": SwimmingPool swimPool = new SwimmingPool(hours); 
                                          swimmingPoolIncome += visit(swimPool);
                                          swimmingPoolHours += hours;
                                          break;
                }
            }
        } 
        catch (IOException e)
        {
            Logger.getLogger(ExtraVisitor.class.getName()).log(Level.SEVERE, null, e);
        }
        
        double totalIncome = gymIncome + spaIncome + poolIncome + functionIncome + conferenceIncome + swimmingPoolIncome;
        
        StringBuilder statsBuilder = new StringBuilder();
        statsBuilder.append(String.format("%d hours reserved in the GYM with an expected income of €%.2f\n", gymHours, gymIncome));
        statsBuilder.append(String.format("%d hours reserved in the SPA with an expected income of €%.2f\n", spaHours, spaIncome));
        statsBuilder.append(String.format("%d hours reserved in the POOL ROOM with an expected income of €%.2f\n", poolHours, poolIncome));
        statsBuilder.append(String.format("%d hours reserved in the FUNCTION ROOM with an expected income of €%.2f\n", functionHours, functionIncome));
        statsBuilder.append(String.format("%d hours reserved in the CONFERENCE ROOM with an expected income of €%.2f\n", conferenceHours, conferenceIncome));
        statsBuilder.append(String.format("%d hours reserved in the SWIMMING POOL with an expected income of €%.2f\n", swimmingPoolHours, swimmingPoolIncome));
        statsBuilder.append(String.format("Total expected income from extras reservation: €%.2f",totalIncome));
        return statsBuilder.toString();
    }
    
    @Override
    public double visit(Spa spa) {
       return spa.getHours() * 50.00;
    }
    
    @Override
    public double visit(PoolRoom poolRoom) {
       return poolRoom.getHours() * 2.00;
    }
    
    @Override
    public double visit(SwimmingPool pool) {
       return pool.getHours() * 5.00;
    }
    
    @Override
    public double visit(Gym gym) {
        return gym.getHours() * 7.00;
    }
    
    @Override
    public double visit(FunctionRoom functionRoom) {
        return functionRoom.getHours() * 60.00;
    }
    
    @Override
    public double visit(ConferenceRoom conferenceRoom) {
        return conferenceRoom.getHours() * 65.00;
    }
}
