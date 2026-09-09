#include <metal_stdlib>
using namespace metal;
struct Struct_2 {
    float4 position;
    float _varying;
};
struct Struct_4 {
    float depth;
    uint sample_mask;
    float color;
};
struct Struct_5 {
    uint index;
};
struct Struct_6 {
    uint index;
};
array<uint, 1> global_0;

struct vertex_Input {
    uint color [[attribute(10)]];
};
struct vertex_Output {
    float4 position [[position]];
    float _varying [[user(loc1)]];
};
[[vertex]]
vertex_Output vertex(vertex_Input in [[stage_in]], uint vertex_index [[vertex_id]], uint instance_index [[instance_id]]) {
    uint color = in.color;
    uint local_0 = ((vertex_index + instance_index) + color);
    return Struct_2(float4(1.0f), float(local_0));
}

struct fragment_Output {
    float depth;
    uint sample_mask;
    float color [[user(loc0)]];
};
[[fragment]]
fragment_Output fragment(bool front_facing [[front_facing]], uint sample_index [[sample_id]], uint sample_mask [[sample_mask]]) {
    uint local_0 = (sample_mask & (1u << sample_index));
    float local_1 = select(0.0f, 1.0f, front_facing);
    return Struct_4(in._varying, local_0, local_1);
}

float select(float arg_0, float arg_1, bool arg_2) {
}

[[kernel]]
void compute(uint3 global_id [[thread_position_in_grid]], uint3 local_id [[thread_position_in_threadgroup]], uint local_index [[thread_index_in_threadgroup]], uint3 wg_id [[threadgroup_position_in_grid]], uint3 num_wgs [[threadgroups_per_grid]]) {
    global_0[0] = ((((global_id[0] + local_id[0]) + local_index) + wg_id[0]) + num_wgs[0]);
}

struct vertex_two_structs_Output {
    float4 position [[position]];
};
[[vertex]]
vertex_two_structs_Output vertex_two_structs() {
    uint local_0 = 2u;
    return float4(float(in1.index), float(in2.index), float(local_0), 0.0f);
}
