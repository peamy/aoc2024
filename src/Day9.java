import Objects.Block;

import java.util.ArrayList;
import java.util.List;

public class Day9 {
    public Day9() {
        part1();
        part2();
    }

    private void part1() {
        String input = Commons.readFileLines("d9ex").get(0);
        int[] blocks = createBlocks(createBlockList(input));
        printMemory(blocks);

        while (isInComplete(blocks)) {
            blocks = moveABlock(blocks);
            printMemory(blocks);
        }

        System.out.println(calculateScore(blocks));
    }

    private void part2() {
        String input = Commons.readFileLines("d9ex").get(0);
        List<Block> blocks = createBlockList(input);
        while (moveABlock(blocks)) {
            printMemory(blocks);
        }
        System.out.println(calculateScore(blocks));
    }

    private boolean moveABlock(List<Block> blocks) {
        List<Block> emptySpaces = blocks.stream().filter(b -> !b.isFile && b.canMove).toList();
        if (emptySpaces.isEmpty()) {
            return false;
        }
        Block emptyBlock = emptySpaces.getFirst();

        List<Block> fileBlocks = blocks.stream().
                filter(b ->
                b.isFile && b.canMove
                        && b.startPosition > emptyBlock.startPosition
                        && b.size <= emptyBlock.size)
                .toList();
        if (fileBlocks.isEmpty()) {
            emptyBlock.canMove = false;
            return true;
        }

        Block file = fileBlocks.getLast();

        file.startPosition = emptyBlock.startPosition;
        emptyBlock.size-=file.size;
        emptyBlock.startPosition+=file.size;

        int emptyBlockindex = blocks.indexOf(emptyBlock);
        blocks.remove(file);
        blocks.add(emptyBlockindex, file);
            
        return true;
    }

    private int[] moveABlock(int[] memory) {
        int firstEmptySpace = -1;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == -1) {
                firstEmptySpace = i;
                break;
            }
        }
        for (int i = memory.length - 1; i >= 0; i--) {
            if (memory[i] != -1) {
                int value = memory[i];
                memory[i] = -1;
                memory[firstEmptySpace] = value;
                break;
            }
        }
        return memory;
    }

    private long calculateScore(List<Block> blocks) {
        long score = 0L;
        for (Block block : blocks.stream().filter(b -> b.isFile).toList()) {
            for (int i = 0; i < block.size; i++) {
                score += (long) (block.startPosition + i) * block.fileId;
            }
        }
        return score;
    }

    private long calculateScore(int[] memory) {
        long result = 0L;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == -1) {
                break;
            }
            result += (long) memory[i] * i;
        }
        return result;
    }

    private boolean isInComplete(int[] memory) {
        boolean foundEmpty = false;
        for (int nr : memory) {
            if (nr == -1) {
                foundEmpty = true;
            } else if (foundEmpty) {
                return true;
            }
        }
        return false;
    }

    private List<Block> createBlockList(String input) {
        List<Block> blocks = new ArrayList<>();

        int currentPosition = 0;
        for (int i = 0; i < input.length(); i++) {
            int number = Integer.parseInt(input.substring(i, i + 1));
            blocks.add(new Block(number, ((i + 1) / 2), currentPosition, i % 2 != 1));
            currentPosition += number;
        }
        return blocks;
    }

    private int[] createBlocks(List<Block> blocks) {
        int[] memoryBlocks = new int[blocks.getLast().startPosition + blocks.getLast().size];
        for (Block block : blocks) {
            for (int i = 0; i < block.size; i++) {
                memoryBlocks[block.startPosition + i] = block.fileId;
            }
        }
        return memoryBlocks;
    }

    private void printMemory(int[] memory) {
        for (int number : memory) {
            System.out.print(number == -1 ? "." : number);
        }
        System.out.println();
    }

    private void printMemory(List<Block> blocks) {
        for (Block block : blocks) {
            for (int i = 0; i < block.size; i++) {
                System.out.print(block.isFile ? block.fileId : ".");
            }
        }
        System.out.println();
    }
}
