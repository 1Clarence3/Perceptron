import java.awt.*;

public class Perceptron {
    private double[][] inputs;
    private double[] weights, output;
    private double theta, n, w1, w2, maxError;
    private int numW, maxEpoch, AndOr;

    public Perceptron(int numWeights,double learningRate,int maxIterator,double maxDiff,double weight1,double weight2,double[][]samples,double[] outputs, int andOr)
    {
        StdDraw.setCanvasSize(600,600);
        StdDraw.setXscale(0,200);
        StdDraw.setYscale(0,200);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();
        n = learningRate;
        maxEpoch = maxIterator;
        maxError = maxDiff;
        w1 = weight1;
        w2 = weight2;
        inputs = samples;
        output = outputs;
        numW = numWeights;
        theta = 1;
        AndOr = andOr;
    }

    public void train()
    {
        int iterator = 1;
        double[] tempOutput = new double[output.length];
        while(iterator < maxEpoch)
        {
            double error = 0;
            for (int i = 0; i < inputs.length; i++)
            {
                double target = output[i];
                double sampleOutput = activate(inputs[i]);
                error = (target - sampleOutput); //Add Math.abs?
                double deltaW = n * error * inputs[i][0];
                w1 += deltaW;
                double deltaW1 = n * error * inputs[i][1];
                w2 += deltaW1;
                double deltaTheta = n * error * (-1);
                theta += deltaTheta;
                tempOutput[i] = sampleOutput;
            }
            int same = 0;
            for(int i = 0; i < output.length; i++)
            {
                if(output[i] == tempOutput[i])
                {
                    same++;
                }
            }
            System.out.println("Epoch: " + iterator);
            System.out.println("Error: " + (4-same));
            draw();
            if(same == 4)
            {
                break;
            }
            iterator++;
        }
        color();
    }

    //Activate values
    public double activate(double[] input)
    {
        if(input[0]*w1 + input[1]*w2 > theta)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public void draw()
    {
        if(AndOr == 0)
        {
            StdDraw.setPenColor(Color.GREEN);
            for(int i = 1; i < inputs.length; i++)
            {
                StdDraw.filledCircle((inputs[i][0]+1)*70,(inputs[i][1]+1)*70,2);
            }
            StdDraw.setPenColor(Color.RED);
            StdDraw.filledCircle((inputs[0][0]+1)*70,(inputs[0][1]+1)*70,2);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.line((inputs[3][0]+1)*70,(inputs[3][1]+1)*70,(inputs[1][0]+1)*70,(inputs[1][1]+1)*70);
            StdDraw.line((inputs[3][0]+1)*70,(inputs[3][1]+1)*70,(inputs[2][0]+1)*70,(inputs[2][1]+1)*70);
            StdDraw.line((75+1)*70,((-w1*(75/w2)+theta/w2)+1)*70, (-10+1)*70,((-w1*(-10/w2)+theta/w2)+1)*70);
        }
        else
        {
            StdDraw.setPenColor(Color.RED);
            for(int i = 0; i < inputs.length-1; i++)
            {
                StdDraw.filledCircle((inputs[i][0]+1)*70,(inputs[i][1]+1)*70,2);
            }
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.filledCircle((inputs[3][0]+1)*70,(inputs[3][1]+1)*70,2);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.line((inputs[0][0]+1)*70,(inputs[0][1]+1)*70,(inputs[1][0]+1)*70,(inputs[1][1]+1)*70);
            StdDraw.line((inputs[0][0]+1)*70,(inputs[0][1]+1)*70,(inputs[2][0]+1)*70,(inputs[2][1]+1)*70);
            StdDraw.line((75+1)*70,((-w1*(75/w2)+theta/w2)+1)*70, (-10+1)*70,((-w1*(-10/w2)+theta/w2)+1)*70);
        }

        StdDraw.show(100);
    }

    public void color()
    {
        double[] x = {(75+1)*70,(-10+1)*70,0};
        double[] y = {((-w1*(75/w2)+theta/w2)+1)*70,((-w1*(-10/w2)+theta/w2)+1)*70,0};
        double[] x1 = {(75+1)*70,(-10+1)*70,200};
        double[] y1 = {((-w1*(75/w2)+theta/w2)+1)*70,((-w1*(-10/w2)+theta/w2)+1)*70,200};
        if(AndOr == 0)
        {
            StdDraw.setPenColor(221,0,0);
            StdDraw.filledPolygon(x,y);
            StdDraw.setPenColor(0,221,0);
            StdDraw.filledPolygon(x1,y1);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.line((inputs[3][0]+1)*70,(inputs[3][1]+1)*70,(inputs[1][0]+1)*70,(inputs[1][1]+1)*70);
            StdDraw.line((inputs[3][0]+1)*70,(inputs[3][1]+1)*70,(inputs[2][0]+1)*70,(inputs[2][1]+1)*70);
            StdDraw.setPenColor(Color.GREEN);
            for(int i = 1; i < inputs.length; i++)
            {
                StdDraw.filledCircle((inputs[i][0]+1)*70,(inputs[i][1]+1)*70,2);
            }
            StdDraw.setPenColor(Color.RED);
            StdDraw.filledCircle((inputs[0][0]+1)*70,(inputs[0][1]+1)*70,2);
        }
        else
        {
            StdDraw.setPenColor(221,0,0);
            StdDraw.filledPolygon(x,y);
            StdDraw.setPenColor(0,221,0);
            StdDraw.filledPolygon(x1,y1);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.line((inputs[0][0]+1)*70,(inputs[0][1]+1)*70,(inputs[1][0]+1)*70,(inputs[1][1]+1)*70);
            StdDraw.line((inputs[0][0]+1)*70,(inputs[0][1]+1)*70,(inputs[2][0]+1)*70,(inputs[2][1]+1)*70);
            StdDraw.setPenColor(Color.RED);
            for(int i = 0; i < inputs.length-1; i++)
            {
                StdDraw.filledCircle((inputs[i][0]+1)*70,(inputs[i][1]+1)*70,2);
            }
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.filledCircle((inputs[3][0]+1)*70,(inputs[3][1]+1)*70,2);
        }
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line((75+1)*70,((-w1*(75/w2)+theta/w2)+1)*70, (-10+1)*70,((-w1*(-10/w2)+theta/w2)+1)*70);
        /*
        for(int i = 0; i < 100; i++)
        {
            for(int j = 0; j < 100; j++)
            {
                if(j < ((-w1*(i/w2)+theta/w2)+1)*70)
                {
                    StdDraw.setPenColor(Color.GREEN);
                    StdDraw.filledCircle(i,j,4);
                }
                else if(j > ((-w1*(i/w2)+theta/w2)+1)*70)
                {
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.filledCircle(i,j,4);
                }
            }
        }
        */
        StdDraw.show();
    }
}
