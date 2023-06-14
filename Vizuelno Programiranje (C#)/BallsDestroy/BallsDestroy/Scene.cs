using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BallsDestroy
{
    [Serializable]
    public class Scene
    {
        public List<Ball> balls { get; set; } = new List<Ball>();
        public static int Height { get; set; }
        public static int Width { get; set; }
        public static int hitBalls { get; set; } = 0;
        public static int missedBalls { get; set; } = 0;

        public Scene(int w, int h) { 
            Height= h;
            Width= w;
        }

        public void AddBall(Ball ball)
        {
            balls.Add(ball);
        }

        public void Draw(Graphics g)
        {
            foreach (Ball ball in balls)
            {
                ball.Draw(g);
            }
        }

        public void Move()
        {
            foreach(Ball ball in balls)
            {
                ball.Move();
            }
        }

        internal int missedBall()
        {
            List<Ball> ballsToRemove = new List<Ball>();
            foreach (Ball ball in balls)
            {
                if (ball.isOut())
                {
                    missedBalls++;
                    ballsToRemove.Add(ball);
                }
            }
            foreach (Ball b in ballsToRemove)
            {
                balls.Remove(b);
            }
            return missedBalls;
        }

        internal void hitBall(Point location)
        {
            List<Ball> ballsToRemove= new List<Ball>();
            foreach (Ball ball in balls)
            {
                if (ball.isHit(location))
                {
                    ball.ballHit();
                    if(ball.level == -1)
                    {
                        ballsToRemove.Add(ball);
                    }
                    
                }
            }

            foreach(Ball b in ballsToRemove)
            {
                balls.Remove(b);
                hitBalls++;
            }
        }
    }
}
