using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Rectangles
{
    public class Scene
    {
        List<Rectangle> rectangles = new List<Rectangle>();
        public Scene() { }
        public void AddRectangle(Rectangle rectangle)
        {
            rectangles.Add(rectangle);
        }

        public void Draw(Graphics g)
        {
            foreach (Rectangle rectangle in rectangles)
            {
                rectangle.Draw(g);
            }
        }


    }
}
