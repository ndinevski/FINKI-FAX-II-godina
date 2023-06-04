using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms.VisualStyles;

namespace BallsHitting
{
    [Serializable]
    public class Scene
    {
        public List<Ball> balls { get; set; } = new List<Ball>();
        public static int Width { get; set; }
        public static int Height { get; set; }
        public Scene(int w, int h) {
            Width = w;
            Height = h;
        }

        public void Draw(Graphics g)
        {
            foreach (Ball b in balls)
            {
                b.Draw(g);
            }
        }

        public void AddBall(Ball b)
        {
            balls.Add(b);
        }

        public void Move()
        {
            foreach (Ball b in balls)
            {
                b.Move();
            }
        }

        internal void CheckHit()
        {
            List<Ball> listToRemove = new List<Ball>();
            List<Ball> listToAdd = new List<Ball>();
            foreach (Ball b in balls)
            {
                foreach(Ball a in balls)
                {
                    if(a!=b)
                    {
                        if (b.CheckHit(a))
                        {
                            int newRadius = (b.Radius- a.Radius) / 2;
                            int newX = Convert.ToInt32((b.Center.X + a.Center.X) / 2);
                            int newY = Convert.ToInt32((b.Center.Y + a.Center.Y) / 2);
                            Point newCenter = new Point(newX, newY);
                            listToRemove.Add(a);
                            listToRemove.Add(b);
                            listToAdd.Add(new Ball(newCenter, Color.Red, 1));
                        }
                    }
                }
            }
            foreach(Ball b in listToRemove)
            {
                balls.Remove(b);
            }
            listToRemove.Clear();
            if (listToAdd.Count > 0)
            {
                Ball ball = listToAdd[listToAdd.Count-1];
                balls.Add(ball);
            }
        }
    }
}
