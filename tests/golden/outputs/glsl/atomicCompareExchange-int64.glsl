#version 450 core
#extension GL_EXT_shader_explicit_arithmetic_types_int64 : require
precision highp float;
precision highp int;

struct Struct_9 {
    int64_t old_value;
    bool exchanged;
};
struct Struct_11 {
    uint64_t old_value;
    bool exchanged;
};
 uint global_0 = 128u;
layout(set = 0, binding = 0) buffer int64_t[128] global_1;
layout(set = 0, binding = 1) buffer uint64_t[128] global_2;

void wgsl_test_atomic_compare_exchange_i64() {
    {
        uint i = 0u;
        while (true) {
            if ((i < global_0)) {
                {
                    int64_t old = atomicLoad(global_1[i]);
                    bool exchanged = false;
                    while (true) {
                        if (!(exchanged)) {
                            int64_t new_ = int64_t((old + 10));
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

int64_t atomicLoad(int64_t arg_0) {
}

Struct_9 atomicCompareExchangeWeak(int64_t arg_0, int64_t arg_1, int64_t arg_2) {
}

void wgsl_test_atomic_compare_exchange_u64() {
    {
        uint i = 0u;
        while (true) {
            if ((i < global_0)) {
                {
                    uint64_t old = atomicLoad(global_2[i]);
                    bool exchanged = false;
                    while (true) {
                        if (!(exchanged)) {
                            uint64_t new_ = uint64_t((old + 10u));
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

uint64_t atomicLoad(uint64_t arg_0) {
}

Struct_11 atomicCompareExchangeWeak(uint64_t arg_0, uint64_t arg_1, uint64_t arg_2) {
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_test_atomic_compare_exchange_i64();
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_test_atomic_compare_exchange_u64();
}
