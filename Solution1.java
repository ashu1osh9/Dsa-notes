
//  ----   Ashutosh   Kumar----  //

/*     */

class Solution {
public:
 long long ans = LLONG_MIN;
    bool isValidBST(TreeNode* root) {
        stack<TreeNode*>st;
        TreeNode* curr = root;
        while(curr != NULL || !st.empty()){


            while(curr){
                st.push(curr);
                curr = curr->left;
            }

            TreeNode * data = st.top();
            st.pop();
            if(data->val<=ans){
    
                return false;
            }
            ans = data->val;

            curr = data ->right;


        }
        return true;
        
    }
};