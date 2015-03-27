/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universal;

/**
 *
 * @author W. Patrick Hooper <wphooper@gmail.com>
 * @version 1
 */
public class ProjectiveTransform {

    int MAX = 3;
    private double[][] m = new double[3][3];

    public ProjectiveTransform() {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (i == j) {
                    m[i][j] = 1;
                } else {
                    m[i][j] = 0;
                }
            }
        }
    }

    public ProjectiveTransform(ProjectiveTransform M) {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                m[i][j] = M.m[i][j];
            }
        }
    }

    // d*I
    public ProjectiveTransform(double d) {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (i == j) {
                    m[i][j] = d;
                } else {
                    m[i][j] = 0;
                }
            }
        }
    }

    //d on diagonal u above it
    public ProjectiveTransform(double d, double u) {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (i == j) {
                    m[i][j] = d;
                } else if (i + 1 == j) {
                    m[i][j] = u;
                } else {
                    m[i][j] = 0;
                }
            }
        }
    }

    public ProjectiveTransform(double m11, double m22, double m33) {
        m[0][0] = m11;
        m[0][1] = 0;
        m[0][2] = 0;
        m[1][0] = 0;
        m[1][1] = m22;
        m[1][2] = 0;
        m[2][0] = 0;
        m[2][1] = 0;
        m[2][2] = m33;
    }

    public ProjectiveTransform(double m11, double m12, double m13,
            double m21, double m22, double m23,
            double m31, double m32, double m33) {
        m[0][0] = m11;
        m[0][1] = m12;
        m[0][2] = m13;
        m[1][0] = m21;
        m[1][1] = m22;
        m[1][2] = m23;
        m[2][0] = m31;
        m[2][1] = m32;
        m[2][2] = m33;
    }

    public ProjectiveTransform(double[][] M) {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                m[i][j] = M[i][j];
            }
        }
    }

    public ProjectiveTransform dot(ProjectiveTransform P) {
        ProjectiveTransform ret = new ProjectiveTransform(0);
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                for (int k = 0; k < MAX; k++) {
                    ret.m[i][j] += m[i][k] * P.m[k][j];
                }
            }
        }
        return ret;
    }

    public Point dot(Point P) {
        double[] ret = new double[3];
        double[] e = P.getProjectiveCoordinates();
        for (int i = 0; i < MAX; i++) {
            for (int k = 0; k < MAX; k++) {
                ret[i] += m[i][k] * e[k];
            }
        }
        return new Point(ret);
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < MAX; i++) {
            str += "\n[";
            for (int j = 0; j < MAX; j++) {
                str += "" + Double.toString(m[i][j]);
                if (j < 2) {
                    str += " ";
                }
            }
            str += "]";
        }
        return str;
    }

    public ProjectiveTransform inverse() {
        return new ProjectiveTransform(
                -(m[1][2] * m[2][1]) + m[1][1] * m[2][2], m[0][2] * m[2][1] - m[0][1] * m[2][2], -(m[0][2] * m[1][1]) + m[0][1] * m[1][2],
                m[1][2] * m[2][0] - m[1][0] * m[2][2], -(m[0][2] * m[2][0]) + m[0][0] * m[2][2], m[0][2] * m[1][0] - m[0][0] * m[1][2],
                -(m[1][1] * m[2][0]) + m[1][0] * m[2][1], m[0][1] * m[2][0] - m[0][0] * m[2][1], -(m[0][1] * m[1][0]) + m[0][0] * m[1][1]);
    }
}

