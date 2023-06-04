using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BallsHitting
{
    [Serializable]
    public class Ball
    {
        public int Radius { get; set; }
        public Color color { get; set; } = Color.Red;
        public Point Center { get; set; }
        public int MovingRight { get; set; } = 0;
        public int MovingUp { get; set; } = 0;
        public int Width { get; set; } = Scene.Width;
        public int Height { get; set; } = Scene.Height;

        Random random { get; set; } = new Random();

        public Ball(Point center, Color color, int MovUp)
        {
            Center = center;
            Radius = random.Next(20, 40);
            this.color = color;
            if(random.Next(0, 2) == 0 && MovUp == 0)
            {
                MovingRight = 1;
            }
            else
            {
                MovingUp = 1;
            }
            
        }

        public void Draw(Graphics g)
        {
            Brush b = new SolidBrush(color);
            g.FillEllipse(b, Center.X-Radius, Center.Y - Radius, 2*Radius, 2*Radius);
            b.Dispose();
        }

        public void Move()
        {
            if(MovingRight == 1) {
                MoveRight();
            }else if(MovingUp == 1) {
                MoveUp();
            }else if(MovingRight == -1)
            {
                MoveLeft();
            }
            else if(MovingUp == -1)
            {
                MoveDown();
            }
        }

        public void MoveRight()
        {
            if(Center.X <= Width-Radius-10) { 
                Center = new Point(Center.X + 10, Center.Y);
            }
            else
            {
                MovingRight = -1;
            }
        }
        public void MoveLeft()
        {
            MovingUp = 0;
            if (Center.X >= 0 + Radius + 10)
            {
                Center = new Point(Center.X - 10, Center.Y);
            }
            else
            {
                MovingRight = 1;
            }
        }
        public void MoveUp()
        {
            MovingRight = 0;
            if (Center.Y >= 0 + Radius + 10)
            {
                Center = new Point(Center.X, Center.Y - 10);
            }
            else
            {
                MovingUp = -1;
            }
        }
        public void MoveDown()
        {
            MovingRight = 0;
            if (Center.Y <= Height - Radius - 10)
            {
                Center = new Point(Center.X, Center.Y + 10);
            }
            else
            {
                MovingUp = 1;
            }
        }

        internal bool CheckHit(Ball a)
        {
            int MaxRadius = Math.Max(a.Radius, Radius);
            if (Math.Sqrt(Math.Pow(Center.X - a.Center.X,2) + Math.Pow(Center.Y - a.Center.Y,2)) < a.Radius + Radius)
            {
                return true;
            }
            return false;
        }
    }
}
