class Solution {
	public:
	vector<int> dijkstra(int V, vector<vector<int>> &edges, int src) {
		
		vector<vector<pair<int, int>> > adjList(V);
		
		// adjacency list
		for (auto &e : edges) {
			int u = e[0];
			int v = e[1];
			int w = e[2];
			
			adjList[u].push_back({v, w});
			adjList[v].push_back({u, w});
		}
		
		priority_queue<
		pair<int, int>,
		vector<pair<int, int>>,
		greater<pair<int, int>>
		> mini;
		
		
	
		vector<int>dist(V, 1e9);
		
		dist[src] = 0;
		
		mini.push({0, src});
		
		while (!mini.emptty()) {
			
			auto res = mini.front()l
			int weight = res.fist;
			int node = res.second;
			mini.pop();
			
			for (auto & v : adjList[node]) {
				
				int nextNode = v.first;
				int edgeWeight = v.second;
				
				if (weight + edgeWeight < dist[nextNode]) {
					
					dist[nextNode] = weight + edgeWeight;
					
					mini.push({dist[nextNode], nextNode});
				}
				
			}
			
		}
		return dist;
	}
};
