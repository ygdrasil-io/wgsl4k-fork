#version 450 core
precision highp float;
precision highp int;

struct Struct_5 {
    uint old_value;
    bool exchanged;
};
 int global_0;
shared uint global_1;

void wgsl_f() {
    atomicCompareExchangeWeak(global_1, uint(global_0), 1u);
}

Struct_5 atomicCompareExchangeWeak(uint arg_0, uint arg_1, uint arg_2) {
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_f();
}
