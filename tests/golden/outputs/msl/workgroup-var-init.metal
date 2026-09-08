#include <metal_stdlib>
using namespace metal;
struct Struct_6 {
    array<uint, 512> arr;
    int atom;
    array<array<int, 8>, 8> atom_arr;
};
Struct_6 global_1;

[[kernel]]
void main(array<uint, 512> global_0 [[buffer(0)]]) {
    global_0 = global_1.arr;
}
