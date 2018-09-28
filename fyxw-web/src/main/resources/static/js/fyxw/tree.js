// 选中父节点时，选中所有子节点
function getChildNodeIdArr(node) {
	var ts = [];
	if (node.nodes) {
		for (x in node.nodes) {
			ts.push(node.nodes[x].nodeId);
			if (node.nodes[x].nodes) {
				var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
				for (j in getNodeDieDai) {
					ts.push(getNodeDieDai[j]);
				}
			}
		}
	} else {
		ts.push(node.nodeId);
	}
	return ts;
}

// 选中所有子节点时，选中父节点 取消子节点时取消父节点
function setParentNodeCheck(node) {
	var parentNode = $("#tree").treeview("getNode", node.parentId);
	if (parentNode.nodes) {
		var checkedCount = 0;
		for (x in parentNode.nodes) {
			if (parentNode.nodes[x].state.checked) {
				checkedCount++;
			} else {
				break;
			}
		}
		if (checkedCount == parentNode.nodes.length) { // 如果子节点全部被选 父全选
			$("#tree").treeview("checkNode", parentNode.nodeId);
			setParentNodeCheck(parentNode);
		} else { // 如果子节点未全部被选 父未全选
			$('#tree').treeview('uncheckNode', parentNode.nodeId);
			setParentNodeCheck(parentNode);
		}
	}
}

// 取消父节点时 取消所有子节点
function getChildNodeUncheck(node) {
	if (node.nodes) {
		var ts = []; // 当前节点子集中未被选中的集合
		for (x in node.nodes) {
			if (!node.nodes[x].state.checked) {
				ts.push(node.nodes[x].nodeId);
			}
			if (node.nodes[x].nodes) {
				var getNodeDieDai = node.nodes[x];
				console.log(getNodeDieDai);
				for (j in getNodeDieDai) {
					if (!getNodeDieDai.nodes[x].state.checked) {
						ts.push(getNodeDieDai[j]);
					}
				}
			}
		}
	}
	return ts;
}

function onNodeChecked(event, node) {// 选中事件
	var selectNodes = getChildNodeIdArr(node); // 获取所有子节点
	if (selectNodes) { // 子节点不为空，则选中所有子节点
		$('#tree').treeview('checkNode', [ selectNodes, {
			silent : true
		} ]);
	}
	var parentNode = $("#tree").treeview("getNode", node.parentId);
	setParentNodeCheck(node);
}

function onNodeUnchecked(event, node) {// 取消选中事件
	// 取消父节点 子节点取消
	var selectNodes = getChildNodeUncheck(node); // 获取未被选中的子节点
	var childNodes = getChildNodeIdArr(node); // 获取所有子节点
	if (selectNodes && selectNodes.length == 0) { // 有子节点且未被选中的子节点数目为0，则取消选中所有子节点
		$('#tree').treeview('uncheckNode', [ childNodes, {
			silent : true
		} ]);
	}
	// 取消节点 父节点取消
	var parentNode = $("#tree").treeview("getNode", node.parentId); // 获取父节点
	var selectNodes = getChildNodeIdArr(node);
	setParentNodeCheck(node);
}