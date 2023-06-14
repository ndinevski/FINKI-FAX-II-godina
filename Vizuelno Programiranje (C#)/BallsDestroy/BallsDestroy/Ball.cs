using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BallsDestroy
{
    [Serializable]
    public class Ball
    {
        public int Radius { get; set; }
        public Point Center { get; set; }
        public Color Color { get; set; }
        public int level { get; set; }
        public int height { get; set; } = Scene.Height;
        public static int width { get; set; } = Scene.Width;
        Random rand = new Random();
        Random rand2 = new Random();

        public Ball()
        {
            int center_x = -10;
            int center_y = rand.Next(0 + 20, height - 20);
            Center = new Point(center_x, center_y);
            Radius = 30;
            this.level = rand2.Next(3);
        }

        public void Draw(Graphics g)
        {
            Color color;
            if (level == 0)
            {
                color = Color.Green;
            }
            else if (level == 1)
            {
                color = Color.Blue;
            }
            else
            {
                color = Color.Red;
            }
            Brush b = new SolidBrush(color);
            g.FillEllipse(b, Center.X - Radius, Center.Y - Radius, 2 * Radius, 2 * Radius);
            b.Dispose();
        }

        public void Move()
        {
            int x = Center.X + 5;
            this.Center = new Point(x, Center.Y);
        }

        internal bool isOut()
        {
            if(Center.X > width+10)
            {
                return true;
            }
            return false;
        }

        public bool ballHit()
        {
            if (level - 1 >= -1)
            {
                level--;
                return true;
            }
            return false;
        }

        internal bool isHit(Point location)
        {
            return Math.Sqrt(Math.Pow(location.X - Center.X, 2) + Math.Pow(location.Y - Center.Y, 2)) <= Radius;
        }
    }
}
