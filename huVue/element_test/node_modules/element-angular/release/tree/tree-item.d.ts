import { ElTree } from './tree';
import { ElTreeModelData } from './tree-props';
export declare class ElTreeItem {
    root: ElTree;
    indent: number;
    model: ElTreeModelData;
    constructor(root: ElTree);
    clickHandle(event: MouseEvent): void;
    iconClickHandle(event: MouseEvent): void;
    checkHandle(): void;
    updateExpanded(): void;
    isLeaf(): boolean;
    itemEmitter(action: string): void;
}
