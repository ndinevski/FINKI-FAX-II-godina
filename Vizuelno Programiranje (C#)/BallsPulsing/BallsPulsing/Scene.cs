using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BallsPulsing
{
    public class Scene
    {
        List<Shape> shapes = new List<Shape>();

        public Scene(){}

        public void addShape(Shape shape)
        {
            shapes.Add(shape);
        }

        public void Draw(Graphics g)
        {
            foreach(Shape shape in shapes)
            {
                shape.Draw(g);
            }
        }


        internal bool isHit(Point location)
        {
            foreach (Shape shape in shapes)
            {
                if (shape.isHit(location))
                {
                    return true;
                }
            }
            return false;
        }

        internal void MoveRight()
        {
            foreach (Shape shape in shapes)
            {
                if (shape.IsSelected())
                {
                    shape.MoveRight();
                }
            }
        }

        internal void Pulse()
        {
            foreach(Shape shape in shapes)
            {
                shape.Pulse();
            }
        }
    }
}
