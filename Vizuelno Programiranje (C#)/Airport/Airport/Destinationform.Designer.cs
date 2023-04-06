namespace Airport
{
    partial class fAddDest
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.btnCancelDest = new System.Windows.Forms.Button();
            this.btnOKDest = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.nudPriceDest = new System.Windows.Forms.NumericUpDown();
            this.nudLengthDest = new System.Windows.Forms.NumericUpDown();
            this.tbNameDest = new System.Windows.Forms.TextBox();
            this.errorProvider1 = new System.Windows.Forms.ErrorProvider(this.components);
            this.groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudPriceDest)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudLengthDest)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.errorProvider1)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.btnCancelDest);
            this.groupBox1.Controls.Add(this.btnOKDest);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.nudPriceDest);
            this.groupBox1.Controls.Add(this.nudLengthDest);
            this.groupBox1.Controls.Add(this.tbNameDest);
            this.groupBox1.Location = new System.Drawing.Point(9, 11);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(391, 232);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "New Destination";
            // 
            // btnCancelDest
            // 
            this.btnCancelDest.Location = new System.Drawing.Point(203, 203);
            this.btnCancelDest.Name = "btnCancelDest";
            this.btnCancelDest.Size = new System.Drawing.Size(171, 23);
            this.btnCancelDest.TabIndex = 8;
            this.btnCancelDest.Text = "Cancel";
            this.btnCancelDest.UseVisualStyleBackColor = true;
            this.btnCancelDest.Click += new System.EventHandler(this.btnCancelDest_Click);
            // 
            // btnOKDest
            // 
            this.btnOKDest.Location = new System.Drawing.Point(16, 203);
            this.btnOKDest.Name = "btnOKDest";
            this.btnOKDest.Size = new System.Drawing.Size(171, 23);
            this.btnOKDest.TabIndex = 7;
            this.btnOKDest.Text = "Save";
            this.btnOKDest.UseVisualStyleBackColor = true;
            this.btnOKDest.Click += new System.EventHandler(this.btnOK_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(13, 142);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(34, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "Price:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(13, 91);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(43, 13);
            this.label2.TabIndex = 4;
            this.label2.Text = "Length:";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(13, 37);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(38, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "Name:";
            // 
            // nudPriceDest
            // 
            this.nudPriceDest.Location = new System.Drawing.Point(13, 158);
            this.nudPriceDest.Maximum = new decimal(new int[] {
            10000,
            0,
            0,
            0});
            this.nudPriceDest.Name = "nudPriceDest";
            this.nudPriceDest.Size = new System.Drawing.Size(126, 20);
            this.nudPriceDest.TabIndex = 2;
            // 
            // nudLengthDest
            // 
            this.nudLengthDest.Location = new System.Drawing.Point(13, 107);
            this.nudLengthDest.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.nudLengthDest.Name = "nudLengthDest";
            this.nudLengthDest.Size = new System.Drawing.Size(126, 20);
            this.nudLengthDest.TabIndex = 1;
            // 
            // tbNameDest
            // 
            this.tbNameDest.Location = new System.Drawing.Point(13, 53);
            this.tbNameDest.Name = "tbNameDest";
            this.tbNameDest.Size = new System.Drawing.Size(347, 20);
            this.tbNameDest.TabIndex = 0;
            this.tbNameDest.Validating += new System.ComponentModel.CancelEventHandler(this.tbNameDest_Validating);
            // 
            // errorProvider1
            // 
            this.errorProvider1.BlinkRate = 1;
            this.errorProvider1.ContainerControl = this;
            // 
            // fAddDest
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(412, 252);
            this.Controls.Add(this.groupBox1);
            this.Name = "fAddDest";
            this.Text = "Add Destination";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudPriceDest)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudLengthDest)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.errorProvider1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.NumericUpDown nudPriceDest;
        private System.Windows.Forms.NumericUpDown nudLengthDest;
        private System.Windows.Forms.TextBox tbNameDest;
        private System.Windows.Forms.Button btnOKDest;
        private System.Windows.Forms.Button btnCancelDest;
        private System.Windows.Forms.ErrorProvider errorProvider1;
    }
}