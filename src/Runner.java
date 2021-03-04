public class Runner {
    public static void main(String [] args)
    {
        int numWeights = 2;
        int maxEpoch = 10000;
        double learningRate = 0.05;
        double maxError = 0.01;
        double w1 = Math.random()*0.5-0.25;
        double w2 = Math.random()*0.5-0.25;
        double[][] samples = {{0,0},
                              {0,1},
                              {1,0},
                              {1,1}};
        double[] outputs = {0,
                            1,
                            1,
                            1,};
        double[][] samples1 = {{0,0},
                               {0,1},
                               {1,0},
                               {1,1}};
        double[] outputs1 = {0,
                             0,
                             0,
                             1,};

        String response = "Y";
        String gate = "a";
        String learnRate = "0";
        int errorResponse = 0;
        while(response.equals("Y") || response.equals("y"))
        {
            errorResponse = 0;
            gate = "a";
            while(!(gate.equals("1") || gate.equals("2")))
            {
                if(errorResponse == 1)
                {
                    javax.swing.JOptionPane.showMessageDialog(null, "Please enter in a \"1\" or \"2\"");
                }
                gate = javax.swing.JOptionPane.showInputDialog(null, "Would you like to see an AND gate or an OR gate? \n1. OR Gate \n2. AND Gate");
                errorResponse = 1;
            }
            errorResponse = 0;
            learnRate = "0";
            while(!(Double.parseDouble(learnRate) > 0 && Double.parseDouble(learnRate) < 10))
            {
                if(errorResponse == 1)
                {
                    javax.swing.JOptionPane.showMessageDialog(null, "Please enter in a double value between 0 and 10 exclusive");
                }
                try
                {
                    learnRate = javax.swing.JOptionPane.showInputDialog(null, "Please enter in a learning rate greater than 0 but less than 10");
                    errorResponse = 0;
                }
                catch(Exception e)
                {
                    errorResponse = 1;

                }
            }
            //OR Gate
            if(gate.equals("1"))
            {
                Perceptron test = new Perceptron(numWeights,learningRate,maxEpoch,maxError,w1,w2,samples,outputs,0);
                test.train();
            }
            //AND Gate
            else
            {
                Perceptron test = new Perceptron(numWeights,learningRate,maxEpoch,maxError,w1,w2,samples1,outputs1,1);
                test.train();
            }
            response = javax.swing.JOptionPane.showInputDialog(null, "Would you like to test another gate? \nNote: Enter \"Y\" for \"Yes\"");
        }

    }
}
