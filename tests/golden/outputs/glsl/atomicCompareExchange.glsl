#version 450 core
precision highp float;
precision highp int;

struct Struct_9 {
    int old_value;
    bool exchanged;
};
struct Struct_11 {
    uint old_value;
    bool exchanged;
};
 uint global_0 = 128u;
layout(set = 0, binding = 0) buffer int[128] global_1;
layout(set = 0, binding = 1) buffer uint[128] global_2;

void wgsl_test_atomic_compare_exchange_i32() {
    {
        uint i = 0u;
        while (true) {
            if ((i < global_0)) {
                {
                    int old = atomicLoad(global_1[i]);
                    bool exchanged = false;
                    while (true) {
                        if (!(exchanged)) {
                            int new_ = floatBitsToInt((intBitsToFloat(old) + 1.0f));
                            Struct_9 result = atomicCompareExchangeWeak(global_1[i], old, new_);
                            old = result.old_value;
                            exchanged = result.exchanged;
                        } else {
                            break;
                        }
                    }
                }
                i = (i + 1u);
            } else {
                break;
            }
        }
    }
}

int atomicLoad(int arg_0) {
}

Struct_9 atomicCompareExchangeWeak(int arg_0, int arg_1, int arg_2) {
}

void wgsl_test_atomic_compare_exchange_u32() {
    {
        uint i = 0u;
        while (true) {
            if ((i < global_0)) {
                {
                    uint old = atomicLoad(global_2[i]);
                    bool exchanged = false;
                    while (true) {
                        if (!(exchanged)) {
                            uint new_ = floatBitsToUint((uintBitsToFloat(old) + 1.0f));
                            Struct_11 result = atomicCompareExchangeWeak(global_2[i], old, new_);
                            old = result.old_value;
                            exchanged = result.exchanged;
                        } else {
                            break;
                        }
                    }
                }
                i = (i + 1u);
            } else {
                break;
            }
        }
    }
}

uint atomicLoad(uint arg_0) {
}

Struct_11 atomicCompareExchangeWeak(uint arg_0, uint arg_1, uint arg_2) {
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_test_atomic_compare_exchange_i32();
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_test_atomic_compare_exchange_u32();
}
