//我们得到了一副藏宝图，藏宝图显示，在一个迷宫中存在着未被世人发现的宝藏。 
//
// 迷宫是一个二维矩阵，用一个字符串数组表示。它标识了唯一的入口（用 'S' 表示），和唯一的宝藏地点（用 'T' 表示）。但是，宝藏被一些隐蔽的机关保护了起
//来。在地图上有若干个机关点（用 'M' 表示），只有所有机关均被触发，才可以拿到宝藏。 
//
// 要保持机关的触发，需要把一个重石放在上面。迷宫中有若干个石堆（用 'O' 表示），每个石堆都有无限个足够触发机关的重石。但是由于石头太重，我们一次只能搬一
//个石头到指定地点。 
//
// 迷宫中同样有一些墙壁（用 '#' 表示），我们不能走入墙壁。剩余的都是可随意通行的点（用 '.' 表示）。石堆、机关、起点和终点（无论是否能拿到宝藏）也是
//可以通行的。 
//
// 我们每步可以选择向上/向下/向左/向右移动一格，并且不能移出迷宫。搬起石头和放下石头不算步数。那么，从起点开始，我们最少需要多少步才能最后拿到宝藏呢？如果
//无法拿到宝藏，返回 -1 。 
//
// 示例 1： 
//
// 
// 输入： ["S#O", "M..", "M.T"] 
//
// 输出：16 
//
// 解释：最优路线为： S->O, cost = 4, 去搬石头 O->第二行的M, cost = 3, M机关触发 第二行的M->O, cost = 3, 
//我们需要继续回去 O 搬石头。 O->第三行的M, cost = 4, 此时所有机关均触发 第三行的M->T, cost = 2，去T点拿宝藏。 总步数为16。
// 
// 
//
// 示例 2： 
//
// 
// 输入： ["S#O", "M.#", "M.T"] 
//
// 输出：-1 
//
// 解释：我们无法搬到石头触发机关 
// 
//
// 示例 3： 
//
// 
// 输入： ["S#O", "M.T", "M.."] 
//
// 输出：17 
//
// 解释：注意终点也是可以通行的。 
// 
//
// 限制： 
//
// 
// 1 <= maze.length <= 100 
// 1 <= maze[i].length <= 100 
// maze[i].length == maze[j].length 
// S 和 T 有且只有一个 
// 0 <= M的数量 <= 16 
// 0 <= O的数量 <= 40，题目保证当迷宫中存在 M 时，一定存在至少一个 O 。 
// 
//

package com.ggox.leetcode.editor.cn;

import java.util.*;

public class XunBao {

	public static void main(String[] args) {
		Solution solution = new XunBao().new Solution();
		/*
			M M M M M
			M S # M M
			M M # T O
		 */
		int i = solution.minimalSteps(new String[] { "MMMMM", "MS#MM", "MM#TO" });
		System.out.println(i);
	}

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {

        private int[][] moveActions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        private int row, col;

        // 位置
        class Location {
            int x;
            int y;

            public Location(int x, int y) {
                this.x = x;
                this.y = y;
            }

			@Override
			public boolean equals(Object o) {
				if (this == o)
					return true;
				if (o == null || getClass() != o.getClass())
					return false;
				Location location = (Location) o;
				return x == location.x && y == location.y;
			}

			@Override
			public int hashCode() {
				return Objects.hash(x, y);
			}

			Location moveBy(int[] action) {
				int nx = x + action[0];
				int ny = y + action[1];
				if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
					return new Location(nx, ny);
				}
				return null;
			}

		}

		public int minimalSteps(String[] maze) {
            row = maze.length;
            col = maze[0].length();

			/* 预处理开始 */
            // 1.找出起点 终点 石头 和 机关
            Location start = null;
            Location end = null;
            List<Location> stones = new ArrayList<>();
            List<Location> barriers = new ArrayList<>();
			for(int i = 0; i < row; i++){
				for (int j = 0; j < col; j++) {
					char now = maze[i].charAt(j);
					Location location = new Location(i, j);
					switch (now) {
						case 'S':
							start = location;
							break;
						case 'T':
							end = location;
							break;
						case 'M':
							barriers.add(location);
							break;
						case 'O':
							stones.add(location);
							break;
						default:
							break;
					}
				}
			}
			if (start == null || end == null) {
				return -1;
			}
			// 2.起点到各个点的最小距离
			Map<Location,Integer> startDist = bfs(start, maze);
			// 如果没有机关
			if (barriers.size() == 0) {
				return startDist.getOrDefault(end, -1);
			}
			// 3.各个机关到各个点的距离 机关与机关 机关与起点 机关与重点的距离
			Map<Location, Map<Location, Integer>> barrierDist = new HashMap<>();
			Map<String, Integer> barrierToOtherDist = new HashMap<>();
			for (Location barrier : barriers) {
				Map<Location, Integer> dd  = bfs(barrier, maze);
				barrierDist.put(barrier, dd);
				// 到结束节点
				barrierToOtherDist.put(uniqueKeyForLocationToLocation(barrier, end), dd.getOrDefault(end,-1));
			}
			for (int i = 0; i < barriers.size(); i++) {
				// 当前机关节点
				Location barrier = barriers.get(i);
				// 计算到开始节点的最短距离，中间需要经过石头
				int tmp = -1;
				// 枚举石头节点
				for (Location stone : stones) {
					// 开始节点到石头节点的距离
					int dist1 = startDist.getOrDefault(stone, -1);
					// 石头节点到机关节点的距离
					int dist2 = barrierDist.get(barrier).getOrDefault(stone, -1);
					if (dist1 != -1 && dist2 != -1) {
						// 取最小值
						if (tmp == -1 || tmp > dist1 + dist2) {
							tmp = dist1 + dist2;
						}
					}
				}
				// 设置到开始节点的最短距离
				barrierToOtherDist.put(uniqueKeyForLocationToLocation(barrier, start), tmp);

				// 计算到其他机关节点的最短距离，中间需要经过石头
				for (int j = i + 1; j < barriers.size(); j++) {
					Location toBarrier = barriers.get(j);
					int mn = -1;
					for (Location stone : stones) {
						int dist1 = barrierDist.get(barrier).getOrDefault(stone,-1);
						int dist2 = barrierDist.get(toBarrier).getOrDefault(stone,-1);
						if (dist1 != -1 && dist2 != -1) {
							if (mn == -1 || mn > dist1 + dist2) {
								mn = dist1 + dist2;
							}
						}
					}
					barrierToOtherDist.put(uniqueKeyForLocationToLocation(barrier, toBarrier), mn);
				}
			}
			/* 预处理完成 */

			// 不能到达的情况判定 其实可以放到预处理中进行提前return，但是为了代码更清晰可读，还是单独判定一下吧
			for (Location barrier : barriers) {
				int toStart = barrierToOtherDist.getOrDefault(uniqueKeyForLocationToLocation(barrier, start), -1);
				int toEnd = barrierToOtherDist.getOrDefault(uniqueKeyForLocationToLocation(barrier, end), -1);
				if (toStart == -1 || toEnd == -1) {
					return -1;
				}
			}

			// 开始压缩状态动态规划 用二进制的每一位表示一个机关是否已经触达
			// dp[mask][i] 表示状态为mask时，停留在第i个机关的最短路径
			int[][] dp = new int[1 << barriers.size()][barriers.size()];
			for (int i = 0; i < (1 << barriers.size()); i++) {
				Arrays.fill(dp[i], -1);
			}
			//初始化，只有自己到达时，最短路径就是到起点的距离，此时 mask = 2^i次方
			for (int i = 0; i < barriers.size(); i++) {
				int mask = 1 << i;
				dp[mask][i] = barrierToOtherDist.getOrDefault(uniqueKeyForLocationToLocation(barriers.get(i), start), -1);
			}

			/*--------------- 循环方式 从小到大 从最小值开始遍历，更新的mask肯定比未更新大 ---------------------*/
//			for (int mask = 1; mask < (1 << barriers.size()); mask++) {
//				// 遍历机关
//				for (int i = 0; i < barriers.size(); i++) {
//					// 此时mask状态不是包含i
//					if ((mask & (1 << i)) != 0) {
//						// 遍历下一个机关 不包含在mask中
//						for (int j = 0; j < barriers.size(); j++) {
//							if ((mask & (1 << j)) == 0) {
//								int nextMask = mask | 1 << j;
//								int tmp = dp[mask][i] + barrierToOtherDist.getOrDefault(uniqueKeyForLocationToLocation(barriers.get(i), barriers.get(j)), -1);
//								if (dp[nextMask][j] == -1 || dp[nextMask][j] > tmp) {
//									dp[nextMask][j] = tmp;
//								}
//							}
//						}
//					}
//				}
//			}

			/*--------------- 记忆化搜索 从大到小 ---------------------*/
			for (int i = 0; i < barriers.size(); i++) {
				dp[(1 << barriers.size()) - 1][i] = dfs(i, (1 << barriers.size()) - 1, dp, barrierToOtherDist, barriers);
			}

			int ret = -1;
			int finalMask = (1 << barriers.size()) - 1;
			for (int i = 0; i < barriers.size(); i++) {
				int tmp = dp[finalMask][i] + barrierToOtherDist.getOrDefault(uniqueKeyForLocationToLocation(barriers.get(i), end), -1);
				if (ret == -1 || ret > tmp){
					ret = tmp;
				}
			}
			return ret;
		}

		private int dfs(int i,int mask, int[][] dp, Map<String, Integer> barrierToOtherDist, List<Location> barriers) {
        	// mask不合法
			if ((mask & (1 << i)) == 0) {
				return -1;
			}
			if (dp[mask][i] != -1) {
				return dp[mask][i];
			}
        	int res = -1;
			for (int j = 0; j < barriers.size(); j++) {
				if (j != i && (mask & (1 << j)) != 0) {
					int preMask = mask ^ (1 << i);
					// 缓存到数组
					dp[preMask][j] = dfs(j, preMask, dp, barrierToOtherDist, barriers);
					int tmp = dp[preMask][j] + barrierToOtherDist.getOrDefault(uniqueKeyForLocationToLocation(barriers.get(i), barriers.get(j)), -1);
					if (res == -1 || res > tmp) {
						res = tmp;
					}
				}
			}
			return res;
		}

		private String uniqueKeyForLocationToLocation(Location start, Location end) {
			if (start.x > end.x || (start.x == end.x && start.y > end.y)) {
				return end.hashCode() + "" + start.hashCode();
			}
			return start.hashCode() + "" + end.hashCode();
		}

		/**
		 * 广度优先搜索，查到start 到 矩阵中各个节点的距离
		 * @param start
		 * @param maze
		 * @return
		 */
		private Map<Location,Integer> bfs(Location start, String[] maze) {
			Map<Location, Integer> startDist = new HashMap<>();
			// 自己到自己 距离0
			startDist.put(start, 0);
			Queue<Location> queue = new LinkedList<>();
			queue.offer(start);
			while (!queue.isEmpty()) {
				Location location = queue.poll();
				for (int[] moveAction : moveActions) {
					Location nextLocation = location.moveBy(moveAction);
					if (nextLocation != null && maze[nextLocation.x].charAt(nextLocation.y) != '#' && !startDist.containsKey(nextLocation)) {
						startDist.put(nextLocation, startDist.get(location) + 1);
						queue.offer(nextLocation);
					}
				}
			}
			return startDist;
		}
	}
	//leetcode submit region end(Prohibit modification and deletion)

}
