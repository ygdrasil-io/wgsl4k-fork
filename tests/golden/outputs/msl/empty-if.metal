#include <metal_stdlib>
using namespace metal;

[[kernel]]
void comp(uint3 id [[thread_position_in_grid]]) {
    if ((id[0] == 0)) {
    }
    (1 + 1);
    return;
}
