class Solution {
public:

    // [l, r] ke andar kitni walls hain, count karta hai
    int countWalls(vector<int>& walls, int l, int r) {

        int cnt = 0;

        for (int w : walls) {

            if (w >= l && w <= r)
                cnt++;
        }

        return cnt;
    }


    /*
        i    = current robot ka index

        dir  = current robot ke RIGHT side wale robot ki direction

               dir = 0  -> next robot LEFT ja raha hai
               dir = 1  -> next robot RIGHT ja raha hai

        Hum robot ko RIGHT se LEFT process kar rahe hain.
    */
    int solve(int i,
              int dir,
              vector<pair<int,int>>& robot,
              vector<int>& walls) {


        // Agar koi robot nahi bacha
        if (i < 0)
            return 0;


        // Current robot ki position
        int pos = robot[i].first;

        // Current robot ki shooting distance
        int dist = robot[i].second;


        // =====================================================
        //                    OPTION 1 : LEFT
        // =====================================================

        /*
            Robot LEFT shoot karega.

            Example:

                left range
                    |
                    v
            --------R
                   pos

            Minimum position = pos - dist
        */

        int left = pos - dist;


        /*
            Lekin previous robot current robot ke LEFT mein hai.

            Current robot previous robot ke through shoot nahi
            kar sakta.

            Isliye previous robot ke just RIGHT tak hi ja sakte hain.

            previous robot = robot[i-1].first

            So:

            left = max(left, previousRobot + 1)
        */

        if (i > 0) {

            left = max(left, robot[i - 1].first + 1);
        }


        /*
            Ab [left, pos] ke andar jitni walls hain
            unko destroy kar sakte hain.
        */

        int leftWalls = countWalls(walls, left, pos);


        /*
            Current robot LEFT gaya.

            Ab previous robot ko process karenge.

            dir = 0

            Kyunki current robot LEFT direction mein gaya hai.
        */

        int takeLeft =
            leftWalls + solve(i - 1, 0, robot, walls);



        // =====================================================
        //                    OPTION 2 : RIGHT
        // =====================================================

        /*
            Robot RIGHT shoot karega.

            Maximum range:

                pos + dist
        */

        int right = pos + dist;


        /*
            Ab sabse important part.

            Current robot ke RIGHT mein ek next robot hai.

            Current robot next robot ke through shoot nahi kar
            sakta.

            Isliye next robot current robot ki RIGHT shooting
            ko block karega.
        */

        if (i + 1 < robot.size()) {

            // Next robot ki position
            int nextPos = robot[i + 1].first;

            // Next robot ki distance
            int nextDist = robot[i + 1].second;


            /*
                CASE 1:

                Next robot LEFT shoot kar raha hai.

                Example:

                Current Robot          Next Robot
                     R  ------------->    R
                                        <---
                                      shooting

                Next robot ki LEFT range:

                    nextPos - nextDist

                Current robot us position tak bhi nahi ja sakta.

                Isliye:

                    nextPos - nextDist - 1
            */

            if (dir == 0) {

                right = min(
                    right,
                    nextPos - nextDist - 1
                );
            }


            /*
                CASE 2:

                Next robot RIGHT shoot kar raha hai.

                Example:

                    Current Robot      Next Robot
                         R ------------> R ------------>

                Current robot next robot ki position tak nahi
                ja sakta.

                Isliye:

                    nextPos - 1
            */

            else {

                right = min(
                    right,
                    nextPos - 1
                );
            }
        }


        /*
            Ab [pos, right] ke andar jitni walls hain,
            unko current robot destroy kar sakta hai.
        */

        int rightWalls = countWalls(walls, pos, right);


        /*
            Current robot RIGHT gaya.

            Ab previous robot ko process karenge.

            dir = 1

            Kyunki current robot RIGHT direction mein gaya hai.
        */

        int takeRight =
            rightWalls + solve(i - 1, 1, robot, walls);



        // =====================================================
        //                    FINAL ANSWER
        // =====================================================

        /*
            Current robot ke paas 2 choices hain:

                1. LEFT
                2. RIGHT

            Jo maximum walls destroy karegi,
            usko choose karenge.
        */

        return max(takeLeft, takeRight);
    }


    int maxWalls(vector<int>& robots,
                 vector<int>& distance,
                 vector<int>& walls) {


        // =====================================================
        //        Robot ko {position, distance} mein store karo
        // =====================================================

        vector<pair<int,int>> robot;


        for (int i = 0; i < robots.size(); i++) {

            robot.push_back({
                robots[i],
                distance[i]
            });
        }


        // =====================================================
        //              Robots ko position ke according sort
        // =====================================================

        sort(robot.begin(), robot.end());


        // Walls bhi sort kar dete hain
        sort(walls.begin(), walls.end());


        /*
            Hum last robot se start kar rahe hain.

            Example:

                R0   R1   R2   R3
                         ^
                         |
                       start

            i = n - 1
        */

        return solve(
            robot.size() - 1,
            1,
            robot,
            walls
        );
    }
};